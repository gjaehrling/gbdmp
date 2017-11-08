select 
    i.film_id, f.title, r.inventory_id, r.rental_date, r.return_date, datediff(r.return_date, r.rental_date) as rental_time, r.customer_id, f.rental_rate, f.rating, cat.name as category_name, p.amount
from
    rental as r
        inner join
    inventory as i ON r.inventory_id = i.inventory_id
		inner join
	film as f on i.film_id = f.film_id
		inner join
	film_category as c on f.film_id = c.film_id
		inner join
	category as cat on c.category_id = cat.category_id
		inner join
	payment as p on r.rental_id = p.rental_id
where r.return_date is not NULL
order by f.film_id
;