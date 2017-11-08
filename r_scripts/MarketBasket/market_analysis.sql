select 
    kdnr,
	auftnr,
	artikel,
	kategorie,
	kategoriebezeichnung, 
    plz,
    ort,
    (year(curdate())) - (year(geburtsdatum)) as 'alter',
    ledig
from
    kunde, auftrag, bestellung, artikel, kategorie
where
	kdnr = kunde and
	auftnr = auftrag and
	artikel = artikelnr and 
	kategorie = kategorienr
limit 20;