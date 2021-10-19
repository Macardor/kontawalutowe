**Konta walutowe API
by Maciej Kaczmarz**

Opis

localhost:8080

**Dane konkretnego uzytkownika (w tym informacje o koncie) udostepniane sa poprzez podanie numeru PESEL w parametrze (GET):**
http://localhost:8080/api/kontawalutowe/user?PESEL=95033011111

**Aby zarejestrowac uzytkownika konieczne jest podanie jego nr PESEL, imienia i nazwiska oraz poczatkowego stanu konta w PLN (POST):**
http://localhost:8080/api/kontawalutowe/user/register

Przyklad danych wejsciowych w formacie JSON:
{
    "pesel": "95033011111",
    "fullName": "Jan Kowalski",
    "balance": "1200"
}

**W celu wymiany waluty uzytkownika na inna nalezy podac jego nr PESEL, walute uzywana do wymiany, docelowa walute, kwote wydawana (uzywana do wymiany)(POST):**
http://localhost:8080/api/kontawalutowe/user/exchange

Przyklad danych wejsciowych w formacie JSON:
{
    "pesel" : "98013111111",
    "from": "pln",
    "to": "usd",
    "amount": "400"
}

API pobiera informacje o obecnym kursie wymiany walut z http://api.nbp.pl/

Projekt zostal zrealizowany przy pomocy jezyka Java i SpringBoot, struktura - Maven, kontrola wersji - GitHub.

Nie posiada persystentnej bazy danych.
