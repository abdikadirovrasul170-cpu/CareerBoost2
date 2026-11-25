const $ = (id) => document.getElementById(id);
const fields = ["title","fullName","email","phone","summary","skills","experience","education"];
let activeId = null;
let debounceTimer;
function value(id) { return $(id).value.trim(); }
function updatePreview() {
  $("pName").textContent = value("fullName") || "YOUR NAME";
  $("pRole").textContent = value("jobTitle") || "YOUR PROFESSIONAL TITLE";
  $("pContact").textContent = [value("email"), value("phone")].filter(Boolean).join(" · ") || "your@email.com · +7 700 000 00 00";
  $("pSummary").textContent = value("summary") || "Your professional summary will appear here. Introduce yourself and highlight what makes you a great candidate.";
  $("pExperience").textContent = value("experience") || "Add your work experience, responsibilities and achievements.";
  $("pEducation").textContent = value("education") || "Add your educational background.";
  const skills = value("skills").split(",").map(s => s.trim()).filter(Boolean);
  $("pSkills").replaceChildren(...(skills.length ? skills : ["Your skills"]).map(skill => {
    const span = document.createElement("span"); span.textContent = skill; return span;
  }));
  $("resumePreview").classList.toggle("classic-theme", value("template") === "classic");
  document.querySelectorAll(".template-card").forEach(card => card.classList.toggle("selected", card.dataset.template === value("template")));
}
function notify(message) {
  $("toast").textContent = message; $("toast").classList.add("show");
  setTimeout(() => $("toast").classList.remove("show"), 2600);
}
function payload() {
  return { title:value("title") || "My resume", fullName:value("fullName"), email:value("email"),
    phone:value("phone"), summary:value("summary"), skills:value("skills"),
    experience:value("experience"), education:value("education"), template:value("template") };
}
async function saveResume(event) {
  if (event) event.preventDefault();
  const data = payload();
  if (!data.fullName || !data.email) { notify("Please enter your name and email."); return; }
  $("saveBtn").disabled = true; $("saveStatus").textContent = "Saving…";
  try {
    const response = await fetch(activeId ? `/api/resumes/${activeId}` : "/api/resumes", {
      method: activeId ? "PUT" : "POST",
      headers: {"Content-Type":"application/json"}, body:JSON.stringify(data)
    });
    if (!response.ok) throw new Error("Could not save resume");
    const saved = await response.json(); activeId = saved.id;
    $("saveStatus").textContent = "✓ Saved";
    notify("Your resume has been saved!");
    await loadResumes();
  } catch (error) {
    $("saveStatus").textContent = "Save failed";
    notify("Server connection error. Start the Spring Boot app first.");
  } finally { $("saveBtn").disabled = false; }
}
async function loadResumes() {
  try {
    const response = await fetch("/api/resumes");
    if (!response.ok) return;
    const items = await response.json();
    let list = $("savedResumes");
    if (!list) {
      list = document.createElement("div"); list.id = "savedResumes"; list.className = "saved-list";
      $("previewPanelAnchor")?.append(list);
    }
    // Keep the saved records available in the API and show the count in the status line.
    $("saveStatus").textContent = items.length ? `✓ ${items.length} saved resume(s)` : "";
  } catch (_) { /* API is unavailable until the backend starts. */ }
}
function clearForm() {
  $("resumeForm").reset(); activeId = null; $("saveStatus").textContent = ""; updatePreview();
}
function newResume() { clearForm(); $("title").focus(); document.querySelector("#builder").scrollIntoView({behavior:"smooth"}); }
fields.concat(["jobTitle","template"]).forEach(id => $(id).addEventListener("input", () => {
  updatePreview();
  if (id !== "template") { clearTimeout(debounceTimer); }
}));
$("resumeForm").addEventListener("submit", saveResume);
$("clearBtn").addEventListener("click", clearForm);
$("printBtn").addEventListener("click", () => window.print());
["newResumeTop","startNow","ctaStart"].forEach(id => $(id).addEventListener("click", newResume));
document.querySelectorAll(".template-card").forEach(card => card.addEventListener("click", () => {
  $("template").value = card.dataset.template; updatePreview();
  document.querySelector("#builder").scrollIntoView({behavior:"smooth"});
}));
$("mobileMenu").addEventListener("click", () => document.querySelector("#builder").scrollIntoView({behavior:"smooth"}));
updatePreview(); loadResumes();
let authMode = "register";
function setAuthMode(mode) {
  authMode = mode;
  $("authTitle").textContent = mode === "register" ? "Create your account" : "Welcome back";
  $("authHint").textContent = mode === "register" ? "Save your resumes and keep your career journey organized." : "Sign in to continue building your resume.";
  $("authNameLabel").hidden = mode !== "register";
  $("authRoleLabel").hidden = mode !== "register";
  $("authName").required = mode === "register";
  $("authSubmit").textContent = mode === "register" ? "Create account →" : "Sign in →";
  $("authSwitch").textContent = mode === "register" ? "Already have an account? Sign in" : "New here? Create an account";
  $("authMessage").textContent = "";
}
$("authOpen").addEventListener("click", () => { setAuthMode("register"); $("authDialog").showModal(); });
$("authClose").addEventListener("click", () => $("authDialog").close());
$("authSwitch").addEventListener("click", () => setAuthMode(authMode === "register" ? "login" : "register"));
$("authForm").addEventListener("submit", async (event) => {
  event.preventDefault();
  const body = authMode === "register"
    ? {fullName:value("authName"),email:value("authEmail"),password:value("authPassword"),role:value("authRole")}
    : {email:value("authEmail"),password:value("authPassword")};
  try {
    const response = await fetch("/api/auth/" + authMode, {method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(body)});
    const data = await response.json();
    if (!response.ok) throw new Error(data.message || "Authentication failed");
    $("authMessage").textContent = "✓ Welcome, " + data.fullName + " (" + data.role + ")";
    $("authOpen").textContent = data.fullName.split(" ")[0];
    setTimeout(() => $("authDialog").close(), 900);
    notify("You are signed in.");
  } catch (error) { $("authMessage").textContent = error.message; }
});
