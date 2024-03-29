import com.liuhongchen.bscommonmodule.pojo.Book;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
/**
* Created by liuhongchen
*/
@Mapper
public interface BookMapper {

	public Book getBookById(@Param(value = "id") Long id)throws Exception;

	public List<Book>	getBookListByMap(Map<String,Object> param)throws Exception;

	public Integer getBookCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertBook(Book book)throws Exception;

	public Integer updateBook(Book book)throws Exception;

	public Integer deleteBookById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteBook(Map<String,List<String>> params);

}
