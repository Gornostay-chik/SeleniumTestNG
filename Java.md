<table>
  <thead>
    <tr>
      <th>Версия</th>
      <th>Нововведения</th>
      <th>Примеры кода</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Java 8</strong></td>
      <td><strong>Лямбда-выражения</strong></td>
      <td>
        <pre><code class="language-java">public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td>Лямбда-выражения упрощают код, делают его более компактным.</td>
      <td>
        <pre><code class="language-java">Collections.sort(names, (a, b) -> a.compareTo(b));</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><strong>Stream API</strong></td>
      <td>
        <pre><code class="language-java">List<String> out2 = names.stream()
    .filter(s -> s.length() <= 4)
    .collect(Collectors.toList());</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td>Stream API позволяет работать с данными как с потоком.</td>
      <td>
        <pre><code class="language-java">List<String> out = new ArrayList<>();
for (String s : names) {
    if (s.length() <= 4) out.add(s);
}</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><strong>Default-методы</strong></td>
      <td>
        <pre><code class="language-java">interface Greeter {
    default void hello() {
        System.out.println("Hello");
    }
}
new Greeter(){}.hello();</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><strong>Новый API даты/времени (java.time)</strong></td>
      <td>
        <pre><code class="language-java">Date old = new Date();
LocalDate today = LocalDate.now();
LocalDate bd = LocalDate.of(1990, Month.MAY, 23);
System.out.println(Period.between(bd, today).getYears());</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><strong>Optional</strong></td>
      <td>
        <pre><code class="language-java">Optional.ofNullable(name)
    .map(String::toUpperCase)
    .ifPresent(System.out::println);</code></pre>
      </td>
    </tr>
    <tr>
      <td><strong>Java 11</strong></td>
      <td><strong>HTTP Client API (java.net.http)</strong></td>
      <td>
        <pre><code class="language-java">var client = HttpClient.newHttpClient();
var req = HttpRequest.newBuilder(URI.create(url))
    .GET().build();
var resp = client.send(req, HttpResponse.BodyHandlers.ofString());</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td>Новый API для работы с HTTP-запросами.</td>
      <td>
        <pre><code class="language-java">HttpURLConnection c =
    (HttpURLConnection)new URL(url).openConnection();
c.setRequestMethod("GET");
try (InputStream in = c.getInputStream()) {
    /*…*/
}</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><strong>Новые методы String</strong>: isBlank(), lines(), repeat()</td>
      <td>
        <pre><code class="language-java"> "  \n ".isBlank();
"one\ntwo".lines().forEach(System.out::println);
"ha".repeat(3);</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><strong>Factory-методы коллекций</strong></td>
      <td>
        <pre><code class="language-java">List.of("A", "B", "C");
Map.of("X", 1, "Y", 2);</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><strong>var для локальных переменных</strong></td>
      <td>
        <pre><code class="language-java">var msg = "Hello, Java 11!";
Runnable r = () -> {
    var upper = msg.toUpperCase();
    System.out.println(upper);
};
new Thread(r).start();</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><strong>Single-file execution</strong></td>
      <td>
        <pre><code class="language-java">// > java Hello.java</code></pre>
      </td>
    </tr>
    <tr>
      <td><strong>Java 17</strong></td>
      <td><strong>Records</strong></td>
      <td>
        <pre><code class="language-java">public record Point(int x, int y) {}</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><strong>Sealed-классы</strong></td>
      <td>
        <pre><code class="language-java">public sealed interface Shape permits Circle, Rectangle {}
public final class Circle implements Shape {}
public non-sealed class Rectangle implements Shape {}</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><strong>Pattern-matching для instanceof</strong></td>
      <td>
        <pre><code class="language-java">Object o = "hello";
if (o instanceof String s) {
    System.out.println(s);
}</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><strong>Улучшённый switch</strong> (arrow‐case, yield)</td>
      <td>
        <pre><code class="language-java">DayOfWeek d = DayOfWeek.WEDNESDAY;
String type = switch (d) {
    case SATURDAY, SUNDAY -> "Weekend";
    default -> { yield "Weekday"; }
};</code></pre>
      </td>
    </tr>
    <tr>
      <td><strong>Java 21</strong></td>
      <td><strong>Виртуальные потоки (Loom)</strong></td>
      <td>
        <pre><code class="language-java">Thread vt = Thread.ofVirtual().start(() -> doWork());
vt.join();</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><strong>Structured Concurrency</strong> (инкубация)</td>
      <td>
        <pre><code class="language-java">try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    var f1 = scope.fork(() -> fetch("A"));
    var f2 = scope.fork(() -> fetch("B"));
    scope.join();
    scope.throwIfFailed();
    System.out.println(f1.resultNow() + f2.resultNow());
}</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><strong>String Templates</strong> (preview)</td>
      <td>
        <pre><code class="language-java">String name = "Alice";
String msg = STR."Hello, \{name.upperCase()}!";</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><strong>Record Patterns</strong> (preview)</td>
      <td>
        <pre><code class="language-java">record Point(int x, int y) {}
Object p = new Point(5, 6);
if (p instanceof Point(int x, int y)) {
    System.out.printf("x=%d, y=%d%n", x, y);
}</code></pre>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><strong>Sequenced Collections API</strong></td>
      <td>
        <pre><code class="language-java">var seqMap = SequencedCollections.sequencedMap();
seqMap.put("one", 1);
String first = seqMap.firstKey();</code></pre>
      </td>
    </tr>
  </tbody>
</table>
