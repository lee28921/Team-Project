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
	
	
}
