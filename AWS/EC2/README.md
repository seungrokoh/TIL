# __AWS EC2__
AWS EC2를 생성하고 웹서버를 구축해보자.

### __환경__
Ubuntu 18.04
Node.js
***
# __Node.js 설치__
[공식 Node.js 바이너리 배포판](https://github.com/nodesource/distributions/blob/master/README.md)에 들어가 INstallation instruction에 나와있는 명령어로 Node.js v11.x 설치하기

    # Using Ubuntu
    curl -sL https://deb.nodesource.com/setup_11.x | sudo -E bash -
    sudo apt-get install -y nodejs

# __npm__

    # Nodejs 사용 설정
    npm init

    # express
    npm install express

# __MariaDB 설치__

__우분투에 MariaDB 서버 설치__

    $ sudo apt-get install mariadb-server

__MariaDB charset 설정__

먼저 설정 파일을 에디터로 연다.

    $ sudo vi /etc/mysql/mariadb.conf.d/50-server.cnf

이후 [mysqlid] 항목 아래 Character sets를 수정한다. **character-set-server** 추가하고 기존 **collation-server** 주석처리 후 새로 unicode_ci 추가

    character-set-server = utf8mb4
    #collation-server    = utf8mb4_general_ci
    collation-server     = utf8mb4_unicode_ci

# __MariaDB root 계정 쉘인증 우회__
mysql -u root -p를 통해 접속했을 때 패스워드가 맞음에도 불구하고 Access denied for user 'root'@'localhost' 가 발생하는 경우가 있다. 이는 **마리아DB 루트 계정에 기본 적용된 unix_socket 인증 플러그인 때문이기 때문에 수정한다.**

    MariaDB > use mysql;
    MariaDB > update user set plugin = '' where user ='root';
    MariaDB > flush privileges;
    MariaDB > exit;

# __새로운 계정 생성 후 root 권한 주기__

    GRANT ALL PRIVILEGES ON *.* '계정명'@'%' IDENTIFIED BY '비밀번호' REQUIRE NONE WITH GRANT OPTION;


# __권한 주기__

    chown -R "username" /var/www/html/folderName
    chmod -R 777 /var/www/html/folderName

***

# References

## MariaDB
[꼼.꼼.한 블로그 - Ubuntu에 MariaDB 설치](https://jimnong.tistory.com/744)



# __더미 데이터__

INSERT INTO `wise`(`id`, `content`, `speaker`, `background_url`, `created_at`, `updated_at`, `hits`) VALUES (1, "인생은 살이 쪗을 때와\n안쪗을 때로 나뉜다", "- 이소라","https://cdn.pixabay.com/photo/2010/12/13/10/12/belly-2473_960_720.jpg",  2019-07-15, 2019-07-15, 654),
INSERT INTO `wise`(`id`, `content`, `speaker`, `background_url`, `created_at`, `updated_at`, `hits`)
VALUES (2, "죽을 것 같지만\n죽지 않습니다", "- 솔라(마마무)", "https://cdn.pixabay.com/photo/2010/12/13/10/08/belly-2354_960_720.jpg",  2019-07-15, 2019-07-15, 31),

INSERT INTO `wise`(`id`, `content`, `speaker`, `background_url`, `created_at`, `updated_at`, `hits`)
VALUES (3, "먹어봤자\n내가 아는 그 맛이다", "- 옥주현","https://cdn.pixabay.com/photo/2014/07/21/12/05/diet-398612_960_720.jpg",  2019-07-15, 2019-07-15, 21221),

INSERT INTO `wise`(`id`, `content`, `speaker`, `background_url`, `created_at`, `updated_at`, `hits`)
VALUES (4, "세 끼 다 먹으면\n살쪄요", "- 김사랑","https://cdn.pixabay.com/photo/2012/02/29/16/00/apple-19309_960_720.jpg",  2019-07-15, 2019-07-15, 313),

INSERT INTO `wise`(`id`, `content`, `speaker`, `background_url`, `created_at`, `updated_at`, `hits`)
VALUES (5, "날씬한 것보다\n달콤한 것은 없어요", "- 케이트 모스", "https://cdn.pixabay.com/photo/2017/09/08/10/27/slimming-2728331_960_720.jpg", 2019-07-15, 2019-07-15, 33),

INSERT INTO `wise`(`id`, `content`, `speaker`, `background_url`, `created_at`, `updated_at`, `hits`)
VALUES (6, "야식이 뭐에요?\n한 번도 먹어본 적이 없어요", "- 김연아","https://cdn.pixabay.com/photo/2017/08/26/13/32/hamburger-2683042_960_720.jpg", 2019-07-15, 2019-07-15, 679),

INSERT INTO `wise`(`id`, `content`, `speaker`, `background_url`, `created_at`, `updated_at`, `hits`)
VALUES (7, "몸매는 헬스장에서 만들고\n체중은 부엌에서 줄이자", "- 이소라","https://cdn.pixabay.com/photo/2016/06/09/16/04/woman-1446029_960_720.jpg", 2019-07-15, 2019-07-15, 6514),
