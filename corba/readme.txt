idlj -fall calc.idl

javac *.java calc_val/*.java

orbd -ORBInitialPort 1050&

java calc_server -ORBInitialPort 1050 -ORBInitialHost localhost&

java calc_client -ORBInitialPort 1050 -ORBInitialHost localhost