package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;
import model.entity.UserBean;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login-servlet")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        //リクエストパラメータの受け取り
        request.setCharacterEncoding("UTF-8");
        String user_id = request.getParameter("user_id");
        String password = request.getParameter("password");


        //DAOオブジェクト生成
        UserDAO userdao = new UserDAO();

        //sessionスコープ生成・
        HttpSession session = request.getSession();

        try {
            boolean logincheck = userdao.logincheck(user_id, password);

            if(logincheck) {
                //-----------入力が正しかった時の処理---------------------------------------------------

                //sessionスコープへログイン状況をセット
                session.setAttribute("login", true);

                //ログインユーザ名と情報を取得してsessionスコープへセット
                String current_user_name = userdao.getUserName(user_id);
                UserBean current_user = userdao.getUser(user_id);
                session.setAttribute("current_user_name", current_user_name);
                session.setAttribute("current_user", current_user);
                session.setAttribute("current_user_id", user_id);

                //メニューセッションへ転送
                RequestDispatcher rd = request.getRequestDispatcher("menu-servlet");
                rd.forward(request, response);

            } else {
                //--------入力が違った時の処理-----------------------------------------------------------
                //空欄をチェック
                List<String> error = new ArrayList<String>();
                if(password.equals("")) {
                    error.add("パスワードが空欄です");
                }
                if(user_id.equals("")) {
                    error.add("ユーザIDが空欄です");
                }
                error.add("ユーザIDかパスワードに誤りがあります");
                request.setAttribute("error", error);

                //sessionスコープへログイン状況をセット（認証不可）
                session.setAttribute("login", false);

                //ログイン画面へ転送
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
            //---------その他SQLなどのエラー--------------------------------------------------------------
        }catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
		
	}

}
