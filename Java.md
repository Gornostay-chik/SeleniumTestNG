# Сравнение фич Java по версиям

| Версия     | Основные нововведения                                                         |
|------------|-------------------------------------------------------------------------------|
| **Java 8** | Лямбда-выражения, Stream API, Default-методы, java.time, Optional             |
| **Java 11**| HTTP Client API, новые методы String, локальный `var`, Single-file execution |
| **Java 17**| Records, Sealed-классы, Pattern-matching, улучшённый `switch`                 |
| **Java 21**| Virtual Threads, Structured Concurrency, String Templates, Record Patterns, Sequenced Collections |

```java
// ===== Java 8: Lambda vs Anonymous Class =====
// До Java 8: громоздкий анонимный класс
Runnable oldRunnable = new Runnable() {
    @Override public void run() { System.out.println("Hello"); }
};
oldRunnable.run();

// С Java 8: лямбда-выражение
Runnable lambdaRunnable = () -> System.out.println("Hello");
// Что даёт: краткий синтаксис, меньше шума вокруг тела метода
lambdaRunnable.run();


// ===== Java 8: Collection Filtering – Loop vs Stream =====
List<String> names = List.of("Ann", "Bob", "Carol");

// До Java 8: явный цикл
List<String> shortNamesOld = new ArrayList<>();
for (String n : names) {
    if (n.length() <= 3) shortNamesOld.add(n);
}
// После Java 8: Stream API
List<String> shortNames = names.stream()
    .filter(n -> n.length() <= 3)
    .collect(Collectors.toList());
// Что даёт: декларативный код, легко цеплять дополнительные операции (map, sorted…)


// ===== Java 8: Default Methods in Interfaces =====
// До Java 8: при добавлении метода ломались все реализации
interface GreeterOld { void hello(); }
class GreeterImplOld implements GreeterOld {
    public void hello() { System.out.println("Hi"); }
    // не было возможности добавить новый метод без правки GreeterImplOld
}

// С Java 8: default-метод прямо в интерфейсе
interface Greeter {
    void hello();
    default void bye() { System.out.println("Bye"); }
}
// Что даёт: расширяемость API без обязательного обновления клиентов


// ===== Java 8: java.time vs java.util.Date =====
// До Java 8: мутируемая и запутанная работа с датами
Date legacyDate = new Date();
Calendar cal = Calendar.getInstance();
cal.setTime(legacyDate);

// С Java 8: чистые неизменяемые объекты
LocalDate today = LocalDate.now();
LocalDate bd = LocalDate.of(1990, Month.MAY, 23);
long years = Period.between(bd, today).getYears();
// Что даёт: thread-safe, читаемость и предсказуемость операций


// ===== Java 8: Optional vs null-check =====
Map<String, String> map = Map.of("key", "value");

// До Java 8: обязательно проверять на null
String valOld = map.get("other");
if (valOld != null) System.out.println(valOld);

// С Java 8: Optional
Optional.ofNullable(map.get("other"))
    .ifPresent(v -> System.out.println("Found: " + v));
// Что даёт: вынуждает обрабатывать отсутствие значения, меньше NPE


// ===== Java 11: HTTP Client vs HttpURLConnection =====
// До Java 11: старый HttpURLConnection
HttpURLConnection conn = (HttpURLConnection)new URL("https://api").openConnection();
conn.setRequestMethod("GET");
int codeOld = conn.getResponseCode();

// С Java 11: встроенный HttpClient с HTTP/2 и асинхронностью
HttpClient client = HttpClient.newHttpClient();
HttpRequest req = HttpRequest.newBuilder(URI.create("https://api")).GET().build();
HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
// Что даёт: простой синхронный и асинхронный API, лучше производительность


// ===== Java 11: String methods =====
String blank = "  ";
// До Java 11: s.trim().isEmpty()
boolean wasBlank = blank.trim().isEmpty();

// С Java 11: isBlank()
boolean isBlank = blank.isBlank();
// Что даёт: более очевидная проверка на «только пробелы»

String multi = "a\nb";
// До Java 11: split("\\R")
String[] linesOld = multi.split("\\R");
// С Java 11: lines()
List<String> lines = multi.lines().toList();
// Что даёт: безопасный стрим строк по умолчанию

String echo = "ha";
// До Java 11: ручной цикл
String repeatedOld = "";
for (int i = 0; i < 3; i++) repeatedOld += echo;
// С Java 11: repeat()
String repeated = echo.repeat(3);
// Что даёт: понятный метод, оптимизированные реализации


// ===== Java 11: var vs explicit type =====
// До Java 11: String msg = "Hello";
String msgOld = "Hello";
// С Java 11: var msg = "Hello";
var msgNew = "Hello";
// Что даёт: меньше повторений типа, код короче, но тип сохраняется статически


// ===== Java 11: Single-file execution =====
// До Java 11: javac + java
// javac Script.java && java Script

// С Java 11: java Script.java
// Что даёт: удобно для одноразовых скриптов и быстрых проверок


// ===== Java 17: Records vs Plain DTO =====
// До Java 17: голый класс с конструкторами и методами
class PersonOld {
    private final String name;
    PersonOld(String n) { name = n; }
    public String name() { return name; }
    @Override public boolean equals(Object o){ /* ... */ } 
    @Override public int hashCode(){ /* ... */ } 
    @Override public String toString(){ return name; }
}

// С Java 17: record
public record Person(String name) {}
// Что даёт: минимум шаблонного кода, equals/hashCode/toString генерируются автоматически


// ===== Java 17: Sealed Classes =====
// До Java 17: неявный, открытый набор наследников
interface ShapeOld {}
class CircleOld implements ShapeOld {}
class SquareOld implements ShapeOld {}

// С Java 17: жестко контролируемый набор подклассов
public sealed interface Shape permits Circle, Square {}
public final class Circle implements Shape {}
public non-sealed class Square implements Shape {}
// Что даёт: безопасность и понятность иерархий, компилятор проверяет «permits»


// ===== Java 17: Pattern-matching instanceof =====
Object obj = "text";

// До Java 17: проверка + приведение
if (obj instanceof String) {
    String s = (String) obj;
    System.out.println(s.length());
}

// С Java 17: объединённая форма
if (obj instanceof String s) {
    System.out.println(s.length());
}
// Что даёт: меньше boilerplate, безопасное приведение внутри условия


// ===== Java 17: Enhanced switch =====
DayOfWeek d = DayOfWeek.MONDAY;

// До Java 17: классический switch + break
String resOld;
switch (d) {
    case SATURDAY: case SUNDAY:
        resOld = "Weekend";
        break;
    default:
        resOld = "Weekday";
}

// С Java 17: выражение switch с arrow / yield
String res = switch (d) {
    case SATURDAY, SUNDAY -> "Weekend";
    default -> { yield "Weekday"; }
};
// Что даёт: выражения, возвращающие значение, явный break/yield, меньше ошибок


// ===== Java 21: Virtual Threads vs Platform Threads =====
// До Java 21: тяжёлые потоки
Thread platform = new Thread(() -> System.out.println("PT")).start();

// С Java 21: виртуальные потоки
Thread vt = Thread.ofVirtual().start(() -> System.out.println("VT"));
vt.join();
// Что даёт: миллионы лёгковесных потоков для высококонкурентных задач


// ===== Java 21: Structured Concurrency vs manual join =====
// До Java 21: явный join или CountDownLatch
ExecutorService es = Executors.newFixedThreadPool(2);
Future<String> f1 = es.submit(() -> "A");
Future<String> f2 = es.submit(() -> "B");
String a = f1.get(); String b = f2.get();

// С Java 21: StructuredTaskScope
try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    var r1 = scope.fork(() -> "A");
    var r2 = scope.fork(() -> "B");
    scope.join(); scope.throwIfFailed();
    System.out.println(r1.resultNow() + ", " + r2.resultNow());
}
// Что даёт: единая точка управления, автоматическое прерывание всех задач при ошибке


// ===== Java 21: String Templates (preview) vs concat =====
String name = "Java";
// До Java 21: String concat или format
String greetOld = "Hello, " + name + "!";
// С Java 21 (preview):
String greet = STR."Hello, \{name.upperCase()}!";
// Что даёт: безопасная интерполяция без SQL/XSS-рисков, компилятор проверяет синтаксис


// ===== Java 21: Record Patterns (preview) vs manual extract =====
record Point(int x, int y) {}
Object p = new Point(1, 2);

// До Java 21: instanceof + каст
if (p instanceof Point) {
    Point pt = (Point) p;
    System.out.println(pt.x() + ", " + pt.y());
}

// С Java 21: сопоставление с паттерном
if (p instanceof Point(int xVal, int yVal)) {
    System.out.println(xVal + ", " + yVal);
}
// Что даёт: сразу распаковываете поля, меньше каста и boilerplate


// ===== Java 21: Sequenced Collections API vs LinkedHashMap =====
// До Java 21: LinkedHashMap для запоминания порядка вставки
Map<String,Integer> oldSeq = new LinkedHashMap<>();
oldSeq.put("one",1); oldSeq.put("two",2);
String firstOld = oldSeq.keySet().iterator().next();

// С Java 21: SequencedMap
var seqMap = SequencedCollections.sequencedMap();
seqMap.put("one",1); seqMap.put("two",2);
String first = seqMap.firstKey();
// Что даёт: API прямо для доступа к первому/последнему элементу, без обхода
