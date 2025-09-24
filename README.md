# UniversityManagementSystem

# Campus Course & Records Manager (CCRM)

A console-based Java SE project for managing students, courses, enrollments, grades, and transcripts.

## 🚀 How to Run

1. Install JDK 17+ (or your version)
2. Clone this repository:
   ```bash
   git clone https://github.com/pranavkr018/UniversityManagementSystem.git
   cd UniversityManagementSystem/src
3. Compile:
   ```bash
   javac -d ../out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })
4. Run:
   ```bash
   java -cp ../out edu.ccrm.cli.MainMenu
   

Evolution of Java (Short Timeline)
   1995: Java 1.0 released (Sun Microsystems)
   2004: Java 5 (Generics, Enums)
   2014: Java 8 (Lambdas, Streams)
   2021+: Modern LTS versions (Java 17, switch expressions)

Java SE vs ME vs EE
   | Edition | Description | Use Case |
   |---|---|---|
   | Java SE | Standard Edition | Desktop/CLI apps |
   | Java EE | Enterprise Edition | Web/Enterprise apps |
   | Java ME | Micro Edition | Mobile/Embedded |

JDK vs JRE vs JVM
   JDK: Development kit (compiler + tools)
   JRE: Runtime environment (libraries + JVM)
   JVM: Virtual machine that executes bytecode

Setup Notes
   Installed JDK 17 on Windows → java -version screenshot included in /screenshots.
   Used Eclipse for development (project setup screenshots included).

Mapping of Features
   | Requirement | File/Class |
   |---|---|
   | Encapsulation | Student.java, Course.java |
   | Inheritance & Abstraction | Person.java (abstract), Student.java, Instructor.java |
   | Polymorphism | TranscriptService (works with Enrollment polymorphically) |
   | Streams API | ImportExportService, CourseService |
   | NIO.2 | BackupService (Files.walk, Files.copy) |
   | Recursion | BackupService.computeDirectorySize() |
   | Singleton | (Optional) AppConfig.java |
   | Builder Pattern | (Optional) Transcript builder |
   
📸 Screenshots

JDK install verification

Eclipse project setup

Program running (menus)

Exported CSV files & backup folders

🎥 Demo Video (Optional)

Link to a short screen recording (2–3 min) showing usage
