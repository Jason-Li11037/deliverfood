# Java basics practice

Small standalone exercises from the Java basics lesson. They are not part of the Gradle build.

Each folder is independent. To run one (Java 21):

```bash
cd 01-hello-world
javac *.java && java Main
```

(Build output `*.class` is git-ignored.)

| Folder | Topic |
| --- | --- |
| `01-hello-world` | Hello World |
| `02-basic-data-types` | primitives and boxed values |
| `03-collections` | `List`, `Map`, `Set` |
| `04-control-flow` | `if`, `for`, for-each, `switch`, `while` |
| `05-score-grade` | `if` / `else` with early return |
| `06-loop-continue-break` | `continue` and `break` in a loop |
| `07a-book-pojo` | a plain class (POJO) |
| `07b-book-record` | the same data as a `record` |
| `08-interface` | interface and implementing classes |
| `09-animal` | interface, abstract class and inheritance |
| `10-inheritance` | overriding a protected method |
