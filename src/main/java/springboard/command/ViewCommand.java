package springboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springboard.model.JDBCTemplateDAO;
import springboard.model.SpringBbsDTO;

/*
 BbsCommandImpl 인터페이스를 구현했으므로 추상메서드인 execute()는 반드시 
 오버라이딩 해야한다. 또한 해당 객체는 부모타입인 BbsCommandImpl로
 참조할 수 있다.
 */
@Service
public class ViewCommand implements BbsCommandImpl{

   @Autowired
   JDBCTemplateDAO dao;
   
   @Override
   public void execute(Model model) {
      //요청 일괄받기
      Map<String, Object> paramMap = model.asMap();
      //현재 Object형으로 저장된 request 객체를 원래의 형으로 형변환 해준다.
      HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
      SpringBbsDTO dto = (SpringBbsDTO)paramMap.get("springBbsDTO");
      

      dto = dao.view(String.valueOf(dto.getIdx()));
      
      //줄바꿈 처리
      dto.setContents(dto.getContents().replace("\r\n", "<br/>"));
      
      //위에서 처리한 목록의 모든 처리결과를 Model 객체에 저장한다.
      model.addAttribute("viewRow", dto);
      model.addAttribute("nowPage", dto.getNowPage());
      
   }
}