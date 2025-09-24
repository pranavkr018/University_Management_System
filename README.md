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
<img width="388" height="776" alt="Screenshot 2025-09-24 102352" src="https://github.com/user-attachments/assets/baa8bab3-09ca-44d1-b51e-654a741690e3" />

Program running (menus)
<img width="1920" height="1008" alt="Screenshot 2025-09-24 102733" src="https://github.com/user-attachments/assets/f9b72c00-9ca4-4754-a78d-8d3f0052126d" />

Adding student details
<img width="714" height="179" alt="Screenshot 2025-09-24 103218" src="https://github.com/user-attachments/assets/d152a1af-c366-4722-ae72-121cc500c073" />

Adding a new course
<img width="729" height="242" alt="Screenshot 2025-09-24 103343" src="https://github.com/user-attachments/assets/eb2a60f9-c280-4c73-a164-7594e28bda7a" />

Adding a new Instructor(faculty)
<img width="679" height="175" alt="Screenshot 2025-09-24 103540" src="https://github.com/user-attachments/assets/e82990ff-a7a7-4c82-975b-566f97aaa0ea" />

Enrolling a student in a course
<img width="507" height="130" alt="Screenshot 2025-09-24 103820" src="https://github.com/user-attachments/assets/7c43c269-74d0-4e5a-bc06-74707fa08d10" />

Assign grade to a student
<img width="741" height="461" alt="Screenshot 2025-09-24 104613" src="https://github.com/user-attachments/assets/986b631c-f4ef-4ea8-9acd-f0d762ad17fc" />

Print Student Transcript
<img width="606" height="236" alt="Screenshot 2025-09-24 104708" src="https://github.com/user-attachments/assets/788c0fdf-6374-435b-a8a7-68f8eab748ac" />

Exported CSV files & backup folders
<img width="433" height="380" alt="Screenshot 2025-09-24 103013" src="https://github.com/user-attachments/assets/a203fb53-7328-462f-9948-9f44f9dd10e1" /><img width="442" height="315" alt="Screenshot 2025-09-24 103025" src="https://github.com/user-attachments/assets/4d7e9bcd-d884-4dac-b7d6-301fa06159ca" />
