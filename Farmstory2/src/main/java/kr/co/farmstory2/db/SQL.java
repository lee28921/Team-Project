package kr.co.farmstory2.db;

public class SQL {
	
	// terms
	public static final String SELECT_TERM = "SELECT * FROM `Terms`";
	
	
	// user
	public static final String INSERT_USER = "INSERT INTO `User` SET "
										+ "`uid`=?, "
										+ "`pass`=SHA2(?, 256), "
										+ "`name`=?, "
										+ "`nick`=?,"
										+ "`email`=?, "
										+ "`hp`=?, "
										+ "`zip`=?, "
										+ "`addr1`=?, "
										+ "`addr2`=?, "
										+ "`regip`=?, "
										+ "`regDate`=NOW()";
	
	public static final String SELECT_USER = "SELECT * FROM `User` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	
	public static final String SELECT_COUNT_UID = "SELECT COUNT(*) FROM `User` WHERE `uid`=?";
	public static final String SELECT_COUNT_NICK = "SELECT COUNT(*) FROM `User` WHERE `nick`=?";
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(*) FROM `User` WHERE `email`=?";
	public static final String SELECT_COUNT_HP = "SELECT COUNT(*) FROM `User` WHERE `hp`=?";
	
	// article
	public static final String INSERT_ARTICLE = "INSERT INTO `Article` SET "
										+ "`cate`=?, "
										+ "`title`=?, "
										+ "`content`=?, "
										+ "`file`=?, "
										+ "`writer`=?, "
										+ "`regip`=?, "
										+ "`rdate`=NOW() ";
	
	public final static String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `Article`";
	
	// file
	public static final String INSERT_FILE = "INSERT INTO `File` SET "
										+ "`ano`=?, "
										+ "`ofile`=?, "
										+ "`sfile`=?, "
										+ "`rdate`=NOW() ";
	
}
