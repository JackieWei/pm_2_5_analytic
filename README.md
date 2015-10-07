# pm_2_5_analytic
A simple demo to show the PM 2.5 data analytic.

Data source, PM2.5 CSV from http://www.stateair.net/web/historical/1/5.html
Data storage and analysis, mysql
Dashboard and report.

Use MYSQL, Navicat
cd mysql\bin
run mysqld.exe --max_allowed_packet=800M

Problems:
1:  com.mysql.jdbc.PacketTooBigException: Packet for query is too large (7385229 > 4194304). You can change this value on the server by setting the max_allowed_packet' variable.

	mysqld.exe --max_allowed_packet=800M

	by http://stackoverflow.com/questions/8062496/how-to-change-max-allowed-packet-size
2:  Datetime, format '2008-04-06 13:00:00'

SQL Expression:

删除无效数据（<0）
delete from hourly where value <= 0

检查是否有无效数据
select * from hourly where value <= 0

最近100天内的北京数据
select * from hourly where city = 'Beijing' AND TO_DAYS(NOW()) - TO_DAYS(date) <= 100 ORDER BY date;

2015年北京数据
select * from hourly where city = 'Beijing' AND YEAR(date)=2015 ORDER BY date;

2015与2014同月份比较
select * from hourly where city = 'Beijing' AND YEAR(date)=2015 AND MONTH(date)=1 ORDER BY date;
select * from hourly where city = 'Beijing' AND YEAR(date)=2014 AND MONTH(date)=1 ORDER BY date;

AVG
select AVG(`value`) from hourly where city = 'Beijing' AND YEAR(date)=2014 AND MONTH(date)=2 ORDER BY date;

MAX
select MAX(`value`) from hourly where city = 'Beijing' AND YEAR(date)=2014 AND MONTH(date)=2 ORDER BY date;

MAX
select MIN(`value`) from hourly where city = 'Beijing' AND YEAR(date)=2014 AND MONTH(date)=2 ORDER BY date;