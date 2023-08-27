DROP DATABASE IF EXISTS bookmanage_library;
CREATE DATABASE IF NOT EXISTS bookmanage_library;
USE bookmanage_library;

DROP TABLE IF EXISTS t_book;
CREATE TABLE IF NOT EXISTS t_book(
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '书籍编号',
    bname VARCHAR(60) NOT NULL COMMENT '书名',
    lang TINYINT(1) NOT NULL DEFAULT 0 COMMENT '书籍语言，0-中文，1-外文',
    num INT NOT NULL COMMENT '书籍总数量',
    lend_num INT NOT NULL DEFAULT 0 COMMENT '已借出数量',
    remain_num INT NOT NULL COMMENT '馆内剩余数量',
    author VARCHAR(60) NOT NULL DEFAULT '佚名，anonymous' COMMENT '作者，默认为佚名',
    publishing VARCHAR(60) COMMENT '出版社'
);

DROP TABLE IF EXISTS t_type;
CREATE TABLE IF NOT EXISTS t_type(
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '书籍类型编号',
    tname VARCHAR(30) NOT NULL COMMENT '书籍类型'
);

DROP TABLE IF EXISTS t_book_type;
CREATE TABLE IF NOT EXISTS t_book_type(
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    bid INT NOT NULL COMMENT '外键-书籍编号',
    tid INT NOT NULL COMMENT '外键-书籍类型编号',
    CONSTRAINT fk_book FOREIGN KEY (bid) REFERENCES t_book(id),
    CONSTRAINT fk_type FOREIGN KEY (tid) REFERENCES t_type(id)
);

INSERT INTO t_book(bname, lang, num, remain_num, author, publishing)
	VALUES ('大明王朝1566', 0, 15, 15, '刘和平', '江苏人民出版社'),
			('鬼吹灯', 0, 10, 10, '天下霸唱', '青岛出版社'),
            ('三体', 0, 15, 15, '刘慈欣', '重庆出版社'),
            ('莫泊桑短片小数精选集', 1, 15, 15, '莫泊桑', '天津人民出版社'),
            ('许三观卖血记', 0, 10, 10, '余华', '作家出版社'),
            ('红高粱', 0, 10, 10, '莫言', '浙江文艺出版社'),
            ('呼兰河传', 0, 15, 15, '萧红', '北京联合出版社'),
            ('舞姬', 1, 10, 10, '森鸥外', '万卷出版社'),
			('历代载籍足征録（影印）', 0, 5, 5, '庄述祖', '北京出版社'),
            ('史略（影印）', 0, 5, 5, '朱堃', '北京出版社'),
            ('中国科学技术馆', 0, 10, 10, '李晓亮', '文物出版社'),
            ('搜狐传奇', 0, 5, 5, '阳光', '辽宁人民出版社'),
            ('中外童话故事', 0, 10, 10, '赵梅生', '中外童话故事编辑部'),
            ('安徒生童话', 0, 15, 15, '安徒生，魏红霞', '北京教育出版社'),
            ('格林童话', 0, 15, 15, '格林兄弟，龚勋', '天地出版社');
    
INSERT INTO t_type(tname) 
VALUES ('小说'),('历史'),('古籍'),('悬疑'),('社会'),('科幻'),('传记'),('儿童读物'),
		('童话'),('当代'),('作品集'),('外国'),('科技');
    
    
    
    
    
    
    
    
    
    
    