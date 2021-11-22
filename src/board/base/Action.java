package board.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Action {
	void execute(HttpServletRequest request, 
			HttpServletResponse response) 
				throws ServletException, IOException;
}
