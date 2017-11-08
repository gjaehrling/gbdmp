select 
    kdnr,
	artikelbezeichnung,
	anzahl,
	kategoriebezeichnung, 
    plz,
    ort,
    (year(curdate())) - (year(geburtsdatum)) as 'alter',
    ledig
from
    kunde, auftrag, bestellung, artikel, kategorie
where
	kunde is not NULL and
	kdnr = kunde and
	auftnr = auftrag and
	artikel = artikelnr and 
	kategorie = kategorienr
;