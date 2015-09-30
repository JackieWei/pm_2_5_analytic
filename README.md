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
