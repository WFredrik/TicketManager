# TicketManager
Ticket manager for theater

**Oppgave 1 Modellklasser**

I denne oppgaven skal du bestemme og lage modellklassene for programmet basert på følgende opplysninger:

- I teaterets hovedsal skal det være 22 rader med 15 seter i hver rad
- Hvert sete er unikt identifisert med rad- og setenummer

Det finnes tre forskjellige typer «kunder» i teateret:
- Tilfeldige:
    - Dette er kunder som ringer og reserverer <u>ett eller flere seter</u> per telefon
    - For hver tilfeldige kunde skal det lagres navn og telefonnummer, samt hvilke seter som er bestilt
    - Når kunden møter fram og henter de reserverte billettene skal dette registreres (du kan anta at kunden henter alle
      eller ingen av de reserverte billettene)
- Privatabonnent:
    - Dette er en person som abonnerer på <u>ett fast sete i salen</u> til alle forestillinger
    - For hver Privatabonnent skal det lagres navn, adresse og telefonnummer, samt hvilket sete det abonneres på
- Bedriftsabonnent:
    - Dette er en bedrift som abonnerer på <u>ett eller flere faste seter</u> i salen til alle forestillinger
    - For hver bedriftsabonnent skal det lagres navn på firmaet, adresse, kontaktperson og telefonnummer, samt hvilke
      seter bedriften abonnerer på