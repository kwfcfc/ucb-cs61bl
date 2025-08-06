# UC Berkeley CS61BL Summer 2025 – Labs and Projects


## About This Repository

- **Course:** CS61BL – Data Structures (Java)
- **Term:** Summer 2025
- **Source:** All labs and projects are cloned from the official 
  [CS61BL Skeleton Repository for SU25](https://github.com/cs61bl/skeleton-su25.git).
- **Auditing** Please seee the 
  [auditing policy](https://cs61bl.org/su25/policies/#auditing-61bl) on CS61BL
  website for available resources (such as autograder or labs).

This repository contains my solutions and work for each lab and project in the
course.

### Why not CS61B?

The public version of
[UC Berkeley CS61B Spring 2021](https://sp21.datastructur.es/) provides all the
resources, but the autograder on gradescope seems broken (July 2025) that shows
the following error.

```
Compilation (0/0.001)

Compiling tests for Lab... 
*** Fatal error during 'run'
```

#### Update

The autograder has been fixed now. 

## Repository Structure

- **Branch-per-Lab/Project:**  
  Each lab and project is organized in its own **separate branch** (e.g., 
  `lab01`, `proj1`, etc.).  
  
  The main branch serves as a reference point; to view or work on a specific
  assignment, simply switch to the corresponding branch and contains the
  changes on devcontainer and other environment setup.

- **`.devcontainer` Folder:**  
  Contains configuration for a **containerized development environment**.

  This environment is ready-to-use for Java development with VSCode extensions
  support.

## Development Environment

### Why Use VS Code over IntelliJ?

- **Resource-Friendly:**  
  Visual Studio Code (VS Code) or (VS Codium) is used as the main editor
  instead of IntelliJ IDEA. Therefore some settings are different from the
  official tutorial on CS61BL for IntelliJ.
- **Lower Memory & CPU Usage:**  
  VS Code provides a lightweight and responsive experience, making it ideal for
  systems with limited resources or when working inside containers.

#### IntelliJ Support via JetBrains Gateway
IntelliJ IDEA is also supported via JetBrains Gateway. The devcontainer
includes the necessary plugins so you can connect and use IntelliJ remotely
inside Docker, even though VS Code is the main editor by default.
### Java Runtime: GraalVM 21 vs OpenJDK 17

- **GraalVM 21** is used as the Java runtime inside the container, instead of
  the standard OpenJDK 17.
    - **Faster Startup:** GraalVM provides significantly faster startup times,
    especially for small Java programs (useful for compiling and running labs).
    - **Lower Memory Usage:** GraalVM is more memory-efficient in many
    scenarios, which is important when running in a containerized environment.
    - **Native-image feature:** GraalVM compile some classes and lab exercises
    into native code.
    - **latest LTS version:** Use JavaSE-21 as the latest LTS version, the
    previous 24 version had som incompatibility with JaCoCo and coverage tets
    in VSCodium.

## Getting Started

1. **Clone the Repository:**
    ```bash
    git clone https://github.com/kwfcfc/ucb-cs61b.git
    ```
2. **Switch to a Lab or Project Branch:**
    ```bash
    git checkout lab01  # Replace `lab01` with your desired branch
    ```
3. **Open in VS Code:**
    - Open the folder in VS Code.
    - If prompted, **"Reopen in Container"** to use the prebuilt development
    environment.

4. **Development Container:**
    - The `devcontainer` setup ensures you have:
        - **VS Code Server** for editing
        - **GraalVM 21** as Java runtime
        - All necessary Java tools and extensions

## Notes

- **This repository is not affiliated with UC Berkeley.**
- **For official course materials and instructions, visit the 
[CS61BL website](https://cs61bl.org/).**
- **All upstream code and assignments come from the 
[official skeleton repo](https://github.com/cs61bl/skeleton-su25.git).**
