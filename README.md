# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Diba Shishegar, S358980, s358980@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 så gikk jeg frem ved å kopiere koden fra kompendiet, 5.2.3a. 
Koden 'p = new Node<>(verdi);' var det eneste jeg gjorde om på. Og dette gjorde jeg fordi 
i konstruktøren så jeg at det er 4 parametere: verdi, node v, node h, forelder. Jeg satt da inn 'verdi,null,null,q'.
Verdi var satt inn fra før, så skrev jeg inn null, null fordi når vi skal legge inn en ny node så har jo ikke den noden 
noen kobling til venstre eller høyre barn/node i treet fra før. Skrev så inn 'q' som forelder slik at vi vet hvor noden skal
settes inn/til hvilken forelder den tilhører.

I oppgave 2 så gikk jeg frem med å først opprette en rot node deretter skrive at antallet er 0 til å begynne med.
Skrev så en if setning hvor jeg sier hvis verdien jeg skriver inn og leter etter er null så returnerer vi 0.

Så en while løkke hvor jeg sier så lenge treet ikke er tomt sammenligner vi verdien vi vil finne med allerede eksisterende
verdier i treet. Hvis vi finner verdien vi leter etter øker vi antallet med 1, hvis cmp er mindre enn 0 går vi til venstre i treet,
hvis cmp er større enn eller LIK går vi til høyre i treet. 

Til slutt returnerer vi antallet.

I oppgave 3 så gikk jeg frem ved å kopiere koden 5.1.7 h) fra kompendiet men fjernet '  p = new Node<>(verdi); ', og
' else return p.verdi;' og erstattet den med 'else return p;' fordi jeg fjernet Node<>(verdi) da trenger jeg ikkep.verdi 
men kun p. 

koden sier da hvis p sin venstre ikke er null, altså har en venstre barn blir da p satt til p sin neste. Enda en if setning
som sier det samme men for det høyre barnet. Hvis ingen av if setningene stemmer returnerer vi bare p.

I nestePostOrden oppretter vi f som da er p.forelder. Deretter følger jeg bulletpointsene fra kompendiet på hvordan man
skal finne neste node i treet:
Postorden:

Hvis p ikke har en forelder (p er rotnoden), så er p den siste i postorden.
Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
Hvis p er venstre barn til sin forelder f, gjelder:
Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
Hvis p ikke er enebarn (dvs. f.høyre er ikke null), så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.

Kommenteret i selve koden hvilken kodebit henger sammen med hvilket punkt.

I oppgave 4 gikk jeg frem ved å forstå at det eneste denne 'oppgave' metoden gjør er å printe ut verdien til noden i treet
for oss. At vi heller bruker denne metoden istedenfor sout eller en main metode for å printe ut.

Fungerer slik at vi finner første noden i postorden rekkefølge, deretter en betingelse i en while løkke hvor vi sier så 
lenge treet ikke er tomt så skriver vi ut verdien til noden i treet ' oppgave.utførOppgave((p.verdi));'. Deretter
beveger vi oss til neste node i POSTORDEN rekkefølge!

Post orden Rekursivt gjør akkurat som oppgaven over bare at vi kan kalle på selve metoden vår 'postOrdenRecursive'.
Først sjekker vi om p sin venstre IKKE er null, hvis den IKKE er null kan vi gå videre  og kalle på metoden 'postOrdenRecursive'
og fylle inn p.venstre, oppgave fordi i koden rett over ser vi at de to paramterene som er tatt inn er 'rot,oppgave'.
Gjør akkurat det samme for p.høyre. 

Om p.venstre!=null skriver vi så ut p sin verdi --> oppgave.utførOppgave(p.verdi)

Om p.høyre!=null skriver vi så ut p sin verdi  --> oppgave.utførOppgave(p.verdi)
