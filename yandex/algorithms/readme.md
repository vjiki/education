javac файл; jar cvfe jar_file classname *.class
java -Dфайл.encoding=UTF8 -d64 -Xmx1024M -Xss1024M -jar jar_file


dmcs -r:System.Numerics -sdk:4 -optimize -o файл
mono исполняемый_файл

kotlinc файл -include-runtime -d jar_file	java -Dфайл.encoding=UTF8 -d64 -Xmx1024M -Xss1024M -jar jar_file
javac файл; jar cvfe jar_file classname *.class	java -Dфайл.encoding=UTF8 -d64 -Xmx1024M -Xss1024M -jar jar_file