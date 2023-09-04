package tacos.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcIngredirntRepository implements IngredientRepository {
    final private JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredirntRepository(JdbcTemplate jdbc){
        this.jdbc=jdbc;
    }
    @Override
    public Iterable<Ingredient> findAll(){
        return jdbc.query("select id, name,type from Ingredient", this::mapRowToIngredient);
    }


    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)throws SQLException{
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
    }
    @Override
    public Ingredient findById(String id) {
        return jdbc.queryForObject("select id, name, type from Ingredient where id=?",
                (rs, rowNum) -> new Ingredient(
                        rs.getString("id"),
                        rs.getString("name"),
                        Ingredient.Type.valueOf(rs.getString("type"))),id);

    }
    @Override
    public Ingredient save(Ingredient ingredient){
        jdbc.update("insert into Ingredient (id, name , Type) values (?, ?, ?",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

}
