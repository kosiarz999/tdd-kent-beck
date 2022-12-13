# TDD by Kent Beck

Przykłady z książki "TDD. Sztuka tworzenia dobrego kodu" Kenta Becka. 

## Portfel wielowalutowy (*Multi-Currency Money*)

Wprowadzenie do TDD. W pierwszej kolejności piszemy prostacki test, który chcemy po prostu zaliczyć łamiąc wszelkie zasady czystego kodu (publiczne pola).  
Główną techniką, którą stosujemy na tym etapie jest **preparacja**. Polega ona na bezpośrednim umieszczaniu w kodzie wartości (stałych), które znajdują się w testach. Dopiero po zaliczeniu testów stopniowo zastępujemy w kodzie stałe zmiennymi. 

## Zdegenerowane obiekty 

Strosujemy **triangulację** by upewnić się, że kod działa jak należy. Polega to na zastosowaniu tego samego testu, ale dla innych danych wejściowych.  
Po zastosowaniu tej techniki okazało się, że obiekt stworzony przez klasę `Dollar` nie był niezmienny. Udało nam się zaliczyć test poprzez powielanie obiektów klasy `Dollar`. 

## Równość dla wszystkich 

Implementujemy metodę `equals()` dzięki triangulacji. 
```java
assertEquals(new Dollar(5), new Dollar(5));
assertNotEquals(new Dollar(5), new Dollar(6));
```

## Prywatność 

W asercjach porównujemy utworzone obiekty zamiast wartości. Dzięki temu nie odwołujemy się w testach bezpośrednio do pola klasy `Dollar`, co pozwala nam uczynić to pole prywatnym. Jest to jeden z etapów na drodze do hermetyzacji klasy. 

## Franki, dolary... (*Franc-ly Speaking*)

Program ma za zadanie obliczać wartości między walutami, więc musimy wprowadzić dodatkową klasę reprezentująca franki. Bezszczelnie kopiujemy kod testów oraz źródłowy i wprowadzamy niezbędne zmiany, aby testy dotyczące klasy `Franc` przeszły. Zarówno kod jak i testy są zduplikowane, ale tym zajmiemy się w dalszej części. 

## Równość dla wszystkich - tak, ale... (*Equality for All, Redux*)

W celu eliminacji zduplikowanego kodu tworzymy nową klasę `Money`, po której będą dziedziczyć klasy `Dollar` oraz `Franc`. Tworzymy wspólne dla wszystkich pole oraz metodę `equals()`. Regularnie odpalamy testy sprawdzając, czy dalej są zielone. 

## Jabłka i pomarańcze 

Dopisujemy test, w którym sprawdzamy równość pomiędzy walutami. Test się załamuje, a my dopisujemy do `equals()` sprawdzanie po klasie.

## Tworzymy obiekty 

Klasy `Dollar` i `Franc` zawierają zduplikowany kod i nic nie usprawiedliwia istnienia ich w takiej postaci. Na drodze do ich eliminacji w klasie `Money` tworzymy metody statyczne do tworzenia obiektów klasy `Dollar` oraz `Franc`. Następnie refaktoryzujemy testy, aby mogły korzystać z tej zmiany. 

## Mnożenie rozdwojone (*Times We're Livin' In*)

Usuwamy zduplikowany kod ostatecznie doprowadzając do tego, że `Money` staje się Fabryką dla podklas. Podklasy stają się jedynie szablonami dla walut. Cała logika znajduje się w `Money`.

## Mnożenie jednolite (*Interesting Times*)

