# CareerBoost

CareerBoost — Java тілінде жасалған мансапты дамытуға арналған консольдік MVP жоба.
Жоба браузерді қажет етпейді: барлық демонстрация VS Code терминалында орындалады.

## Технологиялар
- Java 21
- Maven
- JUnit 5
- Builder, Abstract Factory, Template Method, Strategy, Adapter, Visitor

## VS Code-та іске қосу
1. JDK 21 және Maven орнатыңыз.
2. VS Code-та **File → Open Folder** арқылы репозиторийді ашыңыз.
3. Терминалда жоба түбіріне өтіңіз.
4. Демонстрация: `mvn compile exec:java`
5. Тесттер: `mvn test`
6. Толық тексеру: `mvn clean test`

Егер VS Code-та Java Extension Pack орнатылған болса, `Demo.java` файлын ашып, **Run** батырмасын да қолдануға болады.

## Жоба құрылымы
```text
CareerBoost2/
├── pom.xml
├── README.md
├── SRS.md
├── docs/
│   ├── USER_STORIES.md
│   └── GITHUB_PROJECT_SETUP.md
└── src/
    ├── main/java/kz/careerboost/
    │   ├── Demo.java
    │   ├── CareerBoostApp.java
    │   ├── model/
    │   └── patterns/
    └── test/java/kz/careerboost/
```

Бұл оқу мақсатындағы консольдік MVP. Деректер жоба іске қосылған уақыт ішінде жадыда сақталады; нақты email/SMS немесе сыртқы жұмыс платформаларына қосылмайды.
