package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.InstituitionKoku;
import bean.Koku;
import bean.KokuType;
import bean.Position;
import bean.SkPosition;
import bean.StudentKoku;
import bean.TeacherKoku;
import oracle.jdbc.OracleTypes;

public class KokuController {

	private InstituitionController instCtrl = new InstituitionController();
	private TeacherController teacherCtrl = new TeacherController();
	private StudentController studCtrl = new StudentController();

	public KokuType getKokuTypesByTypeId(String typeId, Connection conn)
			throws SQLException {

		KokuType type = null;

		String sql = "{call ? := getKokuTypesByTypeId(?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, typeId);

		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {

			type = new KokuType();

			type.setKoku_Type(rs.getString(1));
			type.setTypeName(rs.getString(2));
		}

		rs.close();
		cs.close();

		return type;
	}

	public Koku getKokuByKokuId(String kokuId, Connection conn)
			throws SQLException {

		Koku koku = null;

		String sql = "{call ? := getKokuByKokuId(?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, kokuId);

		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {

			koku = new Koku();

			koku.setKokuId(rs.getString(1));
			koku.setKoku_Type(getKokuTypesByTypeId(rs.getString(2), conn));
			koku.setKokuName(rs.getString(3));

		}

		rs.close();
		cs.close();

		return koku;

	}

	public InstituitionKoku getInstKokuByInstKokuId(String instituitionKokuId,
			Connection conn) throws Exception {

		InstituitionKoku instituitionKoku = null;

		String sql = "{call ? := getInstKokuByInstKokuId(?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, instituitionKokuId);

		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {

			instituitionKoku = new InstituitionKoku();

			instituitionKoku.setInstituitionKokuId(rs.getString(1));
			instituitionKoku.setInstituitionId(instCtrl.getInstituitionById(
					rs.getString(2), conn));
			instituitionKoku.setKokuId(getKokuByKokuId(rs.getString(3), conn));
			instituitionKoku.setQuota(rs.getShort(4));

		}

		rs.close();
		cs.close();

		return instituitionKoku;
	}

	public List<TeacherKoku> getTeacherKokuByTeacherId(String teacherId,
			Connection conn) throws Exception {

		TeacherKoku teacherKoku = null;
		List<TeacherKoku> teacherKokus = new ArrayList<TeacherKoku>();

		String sql = "{call ? := getTeacherKokuByTeacherId(?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, teacherId);

		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {
			teacherKoku = new TeacherKoku();

			teacherKoku.setTeacherKokuId(rs.getString(1));
			teacherKoku.setInstituitionKokuId(getInstKokuByInstKokuId(
					rs.getString(2), conn));
			teacherKoku.setTeacherId(teacherCtrl.getTeacherById(
					rs.getString(3), conn));
			teacherKoku.setAssignDate(rs.getString(4));

			teacherKokus.add(teacherKoku);
		}

		rs.close();
		cs.close();

		return teacherKokus;
	}
	
	public ArrayList<StudentKoku> getAllStudentByInstKokuId(String instKokuId, Connection conn) 
			throws Exception {
		ArrayList<StudentKoku> studentKokus = new ArrayList<StudentKoku>();
		
		String sql = "{ call getAllStudentByInstKokuId(?,?) }";
		CallableStatement cs = conn.prepareCall(sql);
		cs.setString(1, instKokuId);
		cs.registerOutParameter(2, OracleTypes.CURSOR);
	
		cs.executeQuery();
		ResultSet rs = (ResultSet)cs.getObject(2);
		
		while(rs.next()){
			StudentKoku studentKoku = new StudentKoku();
			//tempStudent.setName(rs.getString(1));
			studentKoku.setStudentKokuId(rs.getString(1));
			studentKoku.setInstituitionKokuId(getInstKokuByInstKokuId(rs.getString(2), conn));
			studentKoku.setStudentId(studCtrl.getStudentById(rs.getString(3), conn));
			studentKoku.setRegisterDate(rs.getString(4));
			
			studentKokus.add(studentKoku);
		}
		
		rs.close();
		cs.close();
		
		return studentKokus;
	} 
	
	public StudentKoku getStudentKokuByStudentKokuId(String studentKokuId, Connection conn) throws Exception{
		
		StudentKoku studentKoku = null;
		
		String sql = "{call ? := getStudentKokuByStudentKokuId(?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, studentKokuId);
		
		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			studentKoku = new StudentKoku();
			
			studentKoku.setStudentKokuId(rs.getString(1));
			studentKoku.setInstituitionKokuId(getInstKokuByInstKokuId(rs.getString(2), conn));
			studentKoku.setStudentId(studCtrl.getStudentById(rs.getString(3), conn));
			studentKoku.setRegisterDate(rs.getString(4));
		}
		
		rs.close();
		cs.close();
		
		return studentKoku;
		
	}
	
	public Position getPositionByPositionId(String positionId, Connection conn) throws SQLException{
		
		Position position = null;
		
		String sql = "{call ? := getPositionByPositionId(?)}";
		
		CallableStatement cs = conn.prepareCall(sql);
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, positionId);
		
		cs.execute();
		
		ResultSet rs = (ResultSet) cs.getObject(1);
		
		while(rs.next()){
			
			position = new Position();
			
			position.setPosition_id(rs.getString(1));
			position.setPosition_name(rs.getString(2));
			position.setMark(rs.getInt(3));
			
		}
		
		rs.close();
		cs.close();
		
		return position;
		
	}

	public SkPosition getSkPositionByStudentKokuId(String studentKokuId,
			Connection conn) throws Exception {

		SkPosition skPosition = null;

		String sql = "{call ? := getSkPositionByStudentKokuId(?)}";

		CallableStatement cs = conn.prepareCall(sql);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.setString(2, studentKokuId);

		cs.execute();

		ResultSet rs = (ResultSet) cs.getObject(1);

		while (rs.next()) {

			skPosition = new SkPosition();

			skPosition.setSk_PositionId(rs.getString(1));
			skPosition.setStudentKoku(getStudentKokuByStudentKokuId(rs.getString(2), conn));
			skPosition.setPosition(getPositionByPositionId(rs.getString(3), conn));
			skPosition.setPosition_date(rs.getString(4));

		}
		rs.close();
		cs.close();

		return skPosition;
	}

}
