# TDD by Kent Beck

Przykłady z książki "TDD. Sztuka tworzenia dobrego kodu" Kenta Becka. 

## Portfel wielowalutowy (*Multi-Currency Money*)

Wprowadzenie do TDD. W pierwszej kolejności piszemy prostacki test, który chcemy po prostu zaliczyć łamiąc wszelkie zasady czystego kodu (publiczne pola).  
Główną techniką, którą stosujemy na tym etapie jest **preparacja**. Polega ona na bezpośrednim umieszczaniu w kodzie wartości (stałych), które znajdują się w testach. Dopiero po zaliczeniu testów stopniowo zastępujemy w kodzie stałe zmiennymi. 

## Zdegenerowane obiekty (*Degenerate Objects*)

Strosujemy **triangulację** by upewnić się, że kod działa jak należy. Polega to na zastosowaniu tego samego testu, ale dla innych danych wejściowych.  
Po zastosowaniu tej techniki okazało się, że obiekt stworzony przez klasę `Dollar` nie był niezmienny. Udało nam się zaliczyć test poprzez powielanie obiektów klasy `Dollar`. 

## Równość dla wszystkich (*Equality for All*)

Implementujemy metodę `equals()` dzięki triangulacji. 
```java
assertEquals(new Dollar(5), new Dollar(5));
assertNotEquals(new Dollar(5), new Dollar(6));
```

## Prywatność (*Privacy*)

W asercjach porównujemy utworzone obiekty zamiast wartości. Dzięki temu nie odwołujemy się w testach bezpośrednio do pola klasy `Dollar`, co pozwala nam uczynić to pole prywatnym. Jest to jeden z etapów na drodze do hermetyzacji klasy. 

## Franki, dolary... (*Franc-ly Speaking*)

Program ma za zadanie obliczać wartości między walutami, więc musimy wprowadzić dodatkową klasę reprezentująca franki. Bezszczelnie kopiujemy kod testów oraz źródłowy i wprowadzamy niezbędne zmiany, aby testy dotyczące klasy `Franc` przeszły. Zarówno kod jak i testy są zduplikowane, ale tym zajmiemy się w dalszej części. 

## Równość dla wszystkich - tak, ale... (*Equality for All, Redux*)

W celu eliminacji zduplikowanego kodu tworzymy nową klasę `Money`, po której będą dziedziczyć klasy `Dollar` oraz `Franc`. Tworzymy wspólne dla wszystkich pole oraz metodę `equals()`. Regularnie odpalamy testy sprawdzając, czy dalej są zielone. 

## Jabłka i pomarańcze (*Apples and Oranges*)

Dopisujemy test, w którym sprawdzamy równość pomiędzy walutami. Test się załamuje, a my dopisujemy do `equals()` sprawdzanie po klasie.

## Tworzymy obiekty (*Makin' Objects*)

Klasy `Dollar` i `Franc` zawierają zduplikowany kod i nic nie usprawiedliwia istnienia ich w takiej postaci. Na drodze do ich eliminacji w klasie `Money` tworzymy metody statyczne do tworzenia obiektów klasy `Dollar` oraz `Franc`. Następnie refaktoryzujemy testy, aby mogły korzystać z tej zmiany. 

## Mnożenie rozdwojone (*Times We're Livin' In*)

Usuwamy zduplikowany kod ostatecznie doprowadzając do tego, że `Money` staje się Fabryką dla podklas. Podklasy stają się jedynie szablonami dla walut. Cała logika znajduje się w `Money`.

## Mnożenie jednolite (*Interesting Times*)

W podklasach poza konstruktorami została jeszcze metoda `times()`. Ta metoda w obu klasach jest do siebie bardzo podobna, różni się tylko jednym szczegółem (walutą). Dlatego odwołujemy się do „zmiennej instancyjnej” (pola `currency` klasy `Money`) i zwracamy nowy obiekt klasy Money (likwidując abstrakcyjność tej klasy). Niestety testy nie przechodzą, bo wywołując metodę `equals()` porównujemy ze sobą instance klas, co w tym przypadku jest bez sensu. Zmieniamy metodę `equals()` porównując ze sobą ilość i walutę klas. To pozwala nam na ujednolicenie metody `times()`między podklasami i przeniesienie jej do superklasy. 

## Korzenie wszelkiego zła (*The Root of All Evil*)

Z racji tego, że klasa `Dollar` oraz `Franc` zawiera tylko i wyłącznie konstruktor (który i tak tylko i wyłącznie odwołuje się do klasy nadrzędnej) to usuwamy obie klasy. Jednocześnie musimy zmodyfikować część testów, gdy te odwołują się bezpośrednio do samych podklas (lub je usunąć, bo część przestaje być potrzebna). 

## Dodawanie - ostatecznie (*Addition, Finally*)

Robimy wstępną przymiarkę pod dodawanie różnych walut według przelicznika. Tworzymy interfejs `Expression`, który stanowi reprezentację działań na walutach. Klasa `Money` implementuje ten interfejs. Tworzymy atrapę banku, z preparowaną wartością zwrotną tylko po to by zaliczyć test. Test testuje działanie 5$ + 5$ przy wykorzystaniu wcześniej wymienionych klas i interfejsów. 

## Zróbmy to (*Make It*)

Interfejs `Expression` zakontraktował metodę `Money reduce(String to)`. `Expression` został zaimplementowany przez klasę `Money` (gdzie `reduce()` zwraca obiekt klasy `Money`) oraz klasę `Sum`.  
Klasa `Sum` to z kolei nowa klasa, która zajmuje się dodawaniem różnych walut i przechowuje dodajną oraz dodaną walutę (prościej: dwa obiekty `Money`, które będziemy do siebie dodawać). Jednocześnie zawiera metodę `reduce()` zaimplementowaną z interfejsu `Expression`. Na razie `Sum` pozwala na poprawne dodawanie dwóch tych samych walut.  
Jednocześnie dopisujemy logikę do klasy `Bank`, która odwołuje się do metod `reduce()` źródła przekazanego jako parametr do metody `reduce()` klasy `Bank`.  
Poza tym dopisujemy szereg testów testujących wszystkie etapy po drodze. 

## Wymiana (*Change*)

