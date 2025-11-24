-- Find record of max order total
select *
from OnlineRetailSales
where OrderTotal = (
	select max(OrderTotal)
    from OnlineRetailSales
)
;

-- Find sales which are greater than to the average sales of the order total?
select avg(OrderTotal)
from OnlineRetailSales
;

select *
from OnlineRetailSales
where OrderTotal >= (
	select round(avg(OrderTotal), 2)
    from OnlineRetailSales
)
;

select *,
	(
		select round(avg(OrderTotal), 2)
		from OnlineRetailSales
	)
from OnlineRetailSales
;

-- Find customers which are not attending conferences in any of the states?
select *
from OnlineRetailSales ors
;

select First_Name, Last_Name, email, Phone_Number, state
from ConventionAttendees ca
where not exists (
	select custstate
    from OnlineRetailSales ors
    where ors.CustState = ca.state
)
;

-- Find customer's count which are not attending conferences in any of the states?
select state, count(1)
from ConventionAttendees ca
where not exists (
	select custstate
    from OnlineRetailSales ors
    where ors.CustState = ca.state
)
group by state
;


-- find items that have less than the avg amount of products in the stock?
select *
from inventory
;

-- 362.1000
select avg(instock)
from inventory
;

-- uncorelated sub-query
select prodcategory, prodnumber, prodname, instock
from inventory
where instock < (
	select avg(instock)
	from inventory
)
order by instock desc
;

-- corelated sub-query
select prodcategory, prodnumber, prodname, instock
from inventory i
inner join (
	select avg(instock) as avg_stock
	from inventory
) avgs on avgs.avg_stock >= i.instock
order by instock desc
;


-- Write query using CTE (Common Table Expression)
-- find orders which order total is greater thant the avg total prices of all other orders?
-- 1386.50
select round(avg(OrderTotal), 2)
from OnlineRetailSales
;

with avgtotal (avg_total) as
(
	select round(avg(OrderTotal), 2)
	from OnlineRetailSales
)
select *
from OnlineRetailSales, avgtotal
where ordertotal >= avg_total
;


-- 362.1000
select avg(instock) as avg_stock
	from inventory
;

-- Find in stock of items that have less than the average amount of products left in stock?
-- CTE Subquery
with AvgInStock (avg_stock) as
(
	select avg(instock) as avg_stock
	from inventory
)
select prodcategory, prodnumber, prodname, instock
from inventory, AvgInStock
where instock < avg_stock
order by instock desc
;

--
select OrderNumber, OrderDate, CustName, ProdCategory, ProdNumber, ProdName, OrderTotal,
	ROW_NUMBER() OVER(PARTITION BY ProdCategory order by OrderTotal desc) as row_num
	from OnlineRetailSales
;


with CTE_MostOrders as
(
	select OrderNumber, OrderDate, CustName, ProdCategory, ProdNumber, ProdName, OrderTotal,
	ROW_NUMBER() OVER(PARTITION BY ProdCategory order by OrderTotal desc) as row_num
	from OnlineRetailSales
    -- where CustName = 'Nolan Inc.'
)
select *
from CTE_MostOrders
-- where row_num <= 3
where row_num in (1,2,3)
order by ProdCategory, OrderTotal
;

-- Find sessions with previous and next session details.
select Start_Date, End_Date, Session_Name,
-- LAG
LAG(Session_Name, 1, 'No Session') OVER(order by Start_Date) as PreviousSession,
LAG(Start_Date, 1) OVER(order by Start_Date) as PreviousSessionStartTime,

-- LEAD
LEAD(Session_Name, 1, 'No Session') OVER(order by Start_Date) as NextSession,
LEAD(Start_Date, 1) OVER(order by Start_Date) as NextSessionStartTime

from ConferenceSessionInfo
where Room_Name = 'Room 102'
;




-- Find sessions with previous and next session details.
select OrderNumber, OrderDate, Quantity
-- LAG
,LAG(Quantity, 5, 'No Order') OVER(order by OrderDate) as PreviousOrder,
LAG(OrderDate, 5) OVER(order by Quantity) as PreviousOrderOate,

-- LEAD
LEAD(Quantity, 5, 'No Order') OVER(order by OrderDate) as NextOrder,
LEAD(OrderDate, 5) OVER(order by Quantity) as NextOrderDate

from OnlineRetailSales
where ProdCategory = 'Drones'
;

SELECT DATE(OrderDate), count(1)
from OnlineRetailSales
where ProdCategory = 'Drones'
group by DATE(OrderDate)
;


with CTE_DroneOrderByDay as
(
	-- quantity by per day orders
	SELECT DATE(OrderDate) order_date, sum(Quantity) qty_per_day
	from OnlineRetailSales
	where ProdCategory = 'Drones'
	group by DATE(OrderDate)
)
select order_date, qty_per_day
-- LAG
,LAG(order_date, 5) OVER(order by order_date desc) as LastOrderOate
,LAG(qty_per_day, 1, 'No Order') OVER(order by order_date) as LastOrder_1
,LAG(qty_per_day, 5, 'No Order') OVER(order by order_date) as LastOrder_5

-- LEAD
,LEAD(order_date, 5) OVER(order by order_date desc) as NextOrderOate
,LEAD(qty_per_day, 1, 'No Order') OVER(order by order_date) as NextOrder_1
,LEAD(qty_per_day, 5, 'No Order') OVER(order by order_date) as NextOrder_5

from CTE_DroneOrderByDay
;


-- Rank() and Dense_Rank() functions
select *
-- rank()
, RANK() over(order by Last_Name) as emp_rank
, DENSE_RANK() over(order by Last_Name) as emp_dense_rank
from EmployeeDirectory
;

-- first 3 ranks
with CTE_RegisteredRanks as
	(
		select *
		-- DENSE_RANK()
		, DENSE_RANK() over(partition by State order by Registration_Date) as registered_rank
		from ConventionAttendees
	)
select *
from CTE_RegisteredRanks
where registered_rank <= 3
;

-- first 3 ranks
with CTE_RegisterRanks as
	(
		select *
		-- RANK()
		, RANK() over(partition by State order by Registration_Date) as registered_rank
		from ConventionAttendees
	)
select *
from CTE_RegisterRanks
where registered_rank <= 3
;

