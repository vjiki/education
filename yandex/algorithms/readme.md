javac файл; jar cvfe jar_file classname *.class
java -Dфайл.encoding=UTF8 -d64 -Xmx1024M -Xss1024M -jar jar_file


dmcs -r:System.Numerics -sdk:4 -optimize -o файл
mono исполняемый_файл

kotlinc файл -include-runtime -d jar_file	java -Dфайл.encoding=UTF8 -d64 -Xmx1024M -Xss1024M -jar jar_file
javac файл; jar cvfe jar_file classname *.class	java -Dфайл.encoding=UTF8 -d64 -Xmx1024M -Xss1024M -jar jar_file



# Компиляция (если требуется).
# javac $PROG.java; jar cvfe $PROG.jar $PROG *.class
# g++ -lm -O2 -fno-stack-limit -std=c++1z -x c++ $PROG.cpp -o $PROG # Переменная count будет хранить число прошедших тестов.
count=0
for name in tests/??
do
# В этот файл запишем вывод программы. submission_output=${name}.out
# Этот файл содержит эталонный ответ. correct_output=${name}.a
# PROG - ваш скрипт / исполняемый файл / класс в зависимости от языка.
# Вместо diff -w можно использовать кастомный чекер.
python $PROG < $name > $submission_output && diff -w $submission_output $correct_output && count=$((count+1))
# go run $PROG < $name > $submission_output && diff -w $submission_output $correct_output && count=$((count+1)) # ./$PROG < $name > $submission_output && diff -w $submission_output $correct_output && count=$((count+1))
# java $PROG.jar < $name > $submission_output && diff -w $submission_output $correct_output && count=$((count+1)) # node $PROG < $name > $submission_output && diff -w $submission_output $correct_output && count=$((count+1))
done
# По желанию удаляем сгенерированные файлы с получившимися ответами. rm tests/*.out
echo "Tests passed: " $count