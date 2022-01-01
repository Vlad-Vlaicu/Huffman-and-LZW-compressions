||||||||||||||||||          README           ||||||||||||||||||

Vlaicu Vlad - 322CD - 9) problema compresiei

1) --- Workflow ---

Programul are punctul de pornire in clasa Checker, verifica daca exista
minim 60 de teste, iar in caz contrar va genera 60 de teste. Testele sunt
citite si executate, iar resultatul este validat.
Daca rezultatul este valid se va afisa in terminal dimensiunea fisierului
comprimat dupa algoritmul respectiv.

2) --- System requirements ---

 ! Programul a fost creat in Java 17, dar ar trebui sa functioneze
  pentru orice versiune de Java 8+
 ! Calea absoluta catre teste nu trebuie sa contina nicio cifra:
 pentru a rezolva testele, ele sunt citite, insa nu intr-o anumita
 ordine, de aceea numarul testului este scos din calea sa absoluta
 si dat ca parametru pentru generarea fisierelor de iesire. Nu va strica
 propriu-zis programul, insa pot aparea fisiere output care sa se numeasca
 321 in loc de 21 pentru ca exista cifra 3 in calea absoluta a testului.

3) --- Huffman Algorithm ---

 - Algoritmul Huffman functioneaza pe baza arborelui Huffman care este construit
din noduri, prin urmare este creata clasa Node. Fiecare instanta Node are
o frecventa (Integer) care este suma frecventelor nodurilor copii.

 - In arborele Huffman trebuie stocate caractere => este creata clasa Leaf
 care este un Node ce memoreaza un caracter

 - In clasa Huffman se construieste arborele Huffman care va sta la baza
 codificarilor caracterelor.
    - este creata o mapare a caracterelor si a frecventelor acestora
    - fiecarui caracter este asociat o instanta Leaf care este adaugata
    intr-o lista de propritate (care este sortata dupa frecventa nodurilor)
    - sunt scoase cate doua noduri din lista, este creat un nod parinte
    care este adaugat inapoi in lista, se repeta procesul pana cand ramane
    un singur nod in lista care va reprezenta radacina arborelui Huffman

 - Se genereaza codurile caracterelor prin parcurgerea recursiva a arborelui
 Huffman si se stocheaza intr-un HashMap

 - Se parcurge inputul caracter cu caracter si se creaza rezultatul prin concatenarea
 codificarilor fiecarui caracter

 - Decodificarea se realizeaza prin intermediul arborelui Huffman creat la incodare.
    - se parcurge sirul de 0 si 1 si se parcurge arborele pana cand se ajunge la o frunza,
    se concateneaza la rezultat caracterul memorat in nod si se reia procesul.
    - se garanteaza ca vom gasi caracterul asociat codificarii deoarece modul de asociere
    a unui cod fiecarui caracter a fost realizat tinandu-se seama de regula prefixului, anume
    nicio codificare nu va avea un prefix format dintr-o alta codificare.

 - Ideea algoritmului este aceea de a inlocui scrierea unui caracter care necesita 8 biti de memorie
 cu o codificare care foloseste mai putini biti, prin urmare, pentru a evidentia aceasta compresie,
 se scrie in fisierul de output un sir continuu de biti reprezentand codificarea caracterelor, de aceea
 in fisierul de output apare un text non human readable.

 4) --- LempelZivWelch ---

 - Algoritmul LZW functioneaza pe baza unui dictionar care este creat la incodare, insa acesta nu este
 necesar pentru decodare, prin urmare se pot folosi metode statice.

 - Se populeaza dictionarul initial cu caracterele ASCII

 - pentru a adauga un string format din mai multe caractere in dictionar, se parcurge inputul caracter cu caracter,
 se tine in evidenta un string cu intrarea curenta si se verifica daca acesta exista in dictionar. Daca intrarea
 curenta exista in dictionar, se actualizeaza intrarea curenta prin concatenarea caracterului curent la aceasta.
 Daca intrarea nu exista, se adauga in dictionar intrarea curenta + caracterul curent, se adauga in lista de rezultate
 codul intrarii trecute, iar intrarea curenta se reseteaza si ia valoarea caracterului curent. la finalul iteratiei
 caracterelor se mai verifica inca o data daca intrarea curenta poate sa fie bagata in lista de rezultate.

 - pentru decodificare se populeaza dictionarul initial cu caracterele ASCII

 - se ia fiecare cod in parte, daca acesta exista in dictionar, obtinem caracterul, altfel luam caracterele obtinute la
 iteratia anterioara si concatenam la acelasi set de caractere apoi se actualizeaza intrarea curenta.

 - se concateneaza intrarea curenta la rezultat si se creaza o intrare in dictionar.

 - Ideea algoritmului este de a elimina informatia redundanta prin gasirea de paternuri care pot fi reduse la un numar,
 respectiv reducerea numarului de biti folositi stabilind in functie de numarul intrarilor in dictionar.

 - rezultatul algoritmului se reduce la o lista de numere care sunt scrise sub forma unui tipar alcatuit dintr-un
   anumit numar de biti. Pentru a verifica eficienta algoritmului s-a scris in fisier urmatoarea structura:
     - antetul alcatuit din 9 biti care contin dimensiunea in care au fost reprezentate numerele
     - lista de numere in format binar conform tiparului stabilit

 - lungimea sirului binar al uni numar este decis in functie de numarul de intrari in dictionar = log2(n) + 1.

  5) --- Checker ---

  - In checker se verifica existenta testelor prin parcurgerea arborelui de fisiere pornind din directorul curent.
  - Daca testele nu exista sau sunt prea putine, atunci ele sunt generate, am pus o limita hardcodata de 60 de teste
  care poate fi modificata.

    Generarea testelor:

    Exista doua tipuri de teste: unul alcatuit din caractere random din ASCII si unul alcatuit din cuvinte random selectate
    din fisierul words.txt din pachetul content. Fiecare test primeste un input n un numar si construieste un String alcatuit
    din 250 * n caractere sau cuvinte, deci sunt alcatuite teste intre 500 de caractere si 100.000 de caractere.
    Testele sunt generate de clasa GenerateContent din pachetul content.

  - Se ia fiecare test in parte si se genereaza structura de fisiere output. Se executa fiecare algoritm pe inputul respectiv
  si se afiseaza in fisierul de output corespunzator.

    Validarea testelor

  - Pentru verificare s-a luat fisierul de input si rezultatul fiecarui algoritm, s-a folosit metoda de decodificare si
  s-au comparat valorile. Verificarea se face inainte de scrierea in fisiere din urmatoarele motive:
     - sunt valori echivalente
     - algoritmul Huffman necesita transmiterea arborelui Huffman pentru decodificare, asta ar presupune sa il serializez
     si sa il scriu intr-un alt fisier de unde sa il citesc apoi pentru decodificare
     - resultatul algoritmilor a fost scris in fisier printr-un BitSet pentru a scrie un sir de biti comtinuu si nu sub forma
     de string. Este implementata conversia din bitset la string pentru citirea din fisier, dar este redundant
      pentru ca valorile dinainte si dupa conversie sunt echivalente.

  6) --- Utils ---
  - Utils este o clasa utilitara in care sunt implementate diverse conversii

  7) --- Concluzii ---

  - Pentru acest tip de teste generate, Huffman este un algoritm eficient si versatil, spre deosebire de LZW care are nevoie
  de conditii speciale pentru a fi luat in calcul in vederea rezolvarii problemei compresiei.
  -  LZW fiind un algoritm pe baza de dictionar este mai bun decat Huffman in testele cu numar impar (care sunt teste pe baza
  de cuvinte ) si in testele de dimensiuni medii si mari. In mod invers, Huffman este mai bun decat LZW in testele pare ( care
   sunt teste pe baza de random characters) si testele de dimensiuni mici.

  - Nu am scris un Makefile pentru ca Java si pentru ca e dubios terminalul de Intellij care nu recunoaste din comenzi

  8) --- Bibliografie ---

  Codurile pentru algoritmii de compresie au fost luati de aici:
  https://www.youtube.com/watch?v=1KzUikIae6k&ab_channel=Geekific
  https://www.youtube.com/watch?v=21_bJLB7gyU&ab_channel=Geekific






