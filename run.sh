current_path=$(pwd)
working_dirs="discovery-service api-gateway search-service"

# jar 파일 생성
for working_dir in $working_dirs
do
  echo "\n| ${working_dir} build..."
  cd ${current_path}/${working_dir}
  ./gradlew clean build -x test
done

# 도커 컨테이너들 실행
docker-compose up -d
