/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.SQL.DAO;

import com.kam.DBUtil.DBUtil;
import com.kam.SQL.ConnectionFactory;
import com.kam.cms.beans.CategoryBean;
import com.kam.cms.beans.PostBean;
import com.kam.cms.beans.RecipeStepBean;
import com.kam.cms.filecontrol.DirectoryCreator;
import com.kam.cms.filecontrol.DirectoryDeleter;
import com.kam.cms.filecontrol.PostDirectoryCreator;
import com.kam.cms.validators.ParamValidator;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blacksteath
 */

/**
 * 
 * TO DO: READD updatePostTitle, updatePostContent, updatePostSummary, updateRecipeSteps
 */
public class PostDAO {
    public List<PostBean> getPostBeanList(){
        List<PostBean> userList = new ArrayList<>();
        Connection con = getConnection();
        String getUserSQL = "SELECT postId, postTitle, postContent, postSummary, posterId, userName " 
                                    + "FROM posts "
                                    + "JOIN users ON posterId = userId ";
        Statement stmt = DBUtil.createStatement(con);
        ResultSet rs = DBUtil.executeQuery(stmt, getUserSQL);

        try {
            while (rs.next()) {
             PostBean tempPostBean;
            int postId = rs.getInt("postId");
            String postTitle = rs.getString("postTitle");
            String postContent = rs.getString("postContent");
            String postSummary = rs.getString("postSummary");
            int posterId = rs.getInt("posterId");
            String posterName = rs.getString("userName");
            tempPostBean = new PostBean(postId, postTitle, postContent, postSummary, posterId, posterName);
            userList.add(tempPostBean);
                //TO DO; finish by turning the RS into userbeans.
         
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        DBUtil.closeStatement(stmt); 
        DBUtil.closeRS(rs);
        DBUtil.closeConnection(con);
      
    }
        return userList;
        
    }
    
      public List<PostBean> getRecentPostBeanList(){
        List<PostBean> userList = new ArrayList<>();
        Connection con = getConnection();
        String getUserSQL = "SELECT postId, postTitle, postContent, postSummary, posterId, userName " 
                                    + "FROM posts "
                                    + "JOIN users ON posterId = userId "
                                    + "ORDER BY postId DESC "
                                    + "LIMIT 10";
        Statement stmt = DBUtil.createStatement(con);
        ResultSet rs = DBUtil.executeQuery(stmt, getUserSQL);

        try {
            while (rs.next()) {
             PostBean tempPostBean;
            int postId = rs.getInt("postId");
            String postTitle = rs.getString("postTitle");
            String postContent = rs.getString("postContent");
            String postSummary = rs.getString("postSummary");
            int posterId = rs.getInt("posterId");
            String posterName = rs.getString("userName");
            tempPostBean = new PostBean(postId, postTitle, postContent, postSummary, posterId, posterName);
            userList.add(tempPostBean);
         
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        DBUtil.closeStatement(stmt); 
        DBUtil.closeRS(rs);
        DBUtil.closeConnection(con);
      
    }
        return userList;
        
    }
      
       public List<PostBean> getRecentPostBeanList(Integer offset, Integer range){
        List<PostBean> userList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
         ResultSet rs = null;
        String getUserSQL = "SELECT postId, postTitle, postContent, postSummary, posterId, userName " 
                                    + "FROM posts "
                                    + "JOIN users ON posterId = userId "
                                    + "ORDER BY postId DESC "
                                    + "LIMIT ? , ?";
     

        try {
          con = getConnection();
          stmt = con.prepareStatement(getUserSQL);
        stmt.setInt(1, offset);
        stmt.setInt(2, range);
        rs = stmt.executeQuery();
            while (rs.next()) {
             PostBean tempPostBean;
            int postId = rs.getInt("postId");
            String postTitle = rs.getString("postTitle");
            String postContent = rs.getString("postContent");
            String postSummary = rs.getString("postSummary");
            int posterId = rs.getInt("posterId");
            String posterName = rs.getString("userName");
            tempPostBean = new PostBean(postId, postTitle, postContent, postSummary, posterId, posterName);
            userList.add(tempPostBean);
         
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        DBUtil.closeStatement(stmt); 
        DBUtil.closeRS(rs);
        DBUtil.closeConnection(con);
      
    }
        return userList;
        
    }
    
    public PostBean getSpecificPost(String post){
    PostBean returnBean = null;
    Integer postNumber = null;
    
    if(post == null) return returnBean;
    try{
         postNumber = Integer.parseInt(post);
    }
    catch(NumberFormatException ex){
        ex.printStackTrace();
        return returnBean;
    }
    
    Connection con = getConnection();
    ResultSet rs = null;
    PreparedStatement ps = null;
     final String getPostString = "SELECT postId, postTitle, postContent, postSummary, posterId, userName " 
                                    + "FROM posts "
                                    + "JOIN users ON posterId = userId "
                                    +"WHERE postId = ?";
    try{
        ps = con.prepareStatement(getPostString);
        ps.setInt(1, postNumber);
        rs = ps.executeQuery();
        boolean found = rs.next();
        if(found){
            int postId = rs.getInt("postId");
            String postTitle = rs.getString("postTitle");
            String postContent = rs.getString("postContent");
            String postSummary = rs.getString("postSummary");
            int posterId = rs.getInt("posterId");
            String posterName = rs.getString("userName");
            returnBean = new PostBean(postId, postTitle, postContent, postSummary, posterId, posterName);
            return returnBean;
        }
        
    }
    
    catch(SQLException ex){
        ex.printStackTrace();
        
    }
    
    finally{
        DBUtil.closeStatement(ps); 
        DBUtil.closeRS(rs);
        DBUtil.closeConnection(con);
      
    }
    return returnBean;
}
    
    public int insertPost(PostBean newPost){
        PostBean usePost = newPost;
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int returnPostId = -1;
        String insertPostSQL = "INSERT INTO posts (postTitle, postContent, postContentNoHtml, postSummary, posterId) "
                              +"VALUES (?,?,?,?,?)";
        
        try{
            
            ps = con.prepareStatement(insertPostSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usePost.getPostTitle());
            ps.setString(2, usePost.getPostContent());
            ps.setString(3, usePost.getPostContentNoHtml());
            ps.setString(4, usePost.getPostSummary());
            ps.setInt(5, usePost.getPosterId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                returnPostId = rs.getInt(1);
                System.out.println("The return post id is coming out as: " + returnPostId );
            }
            
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
        finally{
            DBUtil.closeConnection(con);
            DBUtil.closeRS(rs);
            DBUtil.closeStatement(ps);
           
        }
        System.out.println("PostId:" + returnPostId);
        return returnPostId;
        
    }
    
    public boolean updatePostTitle(String postID, String newPostTitle)
  {
    boolean updated = false;
    if ((!ParamValidator.isValidPostId(postID)) || (ParamValidator.isEmpty(newPostTitle))) {
      return updated;
    }
    Integer postId = Integer.parseInt(postID);
    Connection con = null;
    PreparedStatement ps = null;
    
    String updateTitleSQL = "UPDATE posts "
                            + "SET postTitle = ? "
                            + "WHERE postId = ?";
    try
    {
      con = getConnection();
      ps = con.prepareStatement(updateTitleSQL);
      ps.setString(1, newPostTitle);
      ps.setInt(2, postId);
      ps.executeUpdate();
      updated = true;
    }
    catch (SQLException ex)
    {
      ex.printStackTrace();
    }
    finally
    {
      DBUtil.closeStatement(ps);
      DBUtil.closeConnection(con);
    }
    return updated;
  }
  
  public boolean updatePostContent(String postID, String newPostContent)
  {
    boolean updated = false;
    if ((!ParamValidator.isValidPostId(postID)) || (ParamValidator.isEmpty(newPostContent))) {
      return updated;
    }
    Integer postId = Integer.parseInt(postID);
    Connection con = null;
    PreparedStatement ps = null;
    String updatePostContentSQL = "UPDATE posts "
                                + "SET postContent = ? "
                                + "WHERE postId = ?";
    try
    {
      con = getConnection();
      ps = con.prepareStatement("UPDATE posts SET postContent = ? WHERE postId = ?");
      ps.setString(1, newPostContent);
      ps.setInt(2, postId);
      ps.executeUpdate();
      updated = true;
    }
    catch (SQLException ex)
    {
      ex.printStackTrace();
      System.out.println("Couldn't Update Post Content");
    }
    finally
    {
      DBUtil.closeStatement(ps);
      DBUtil.closeConnection(con);
    }
    return updated;
  }
  
  public boolean updatePostSummary(String postID, String newPostSummary)
  {
    boolean updated = false;
    if ((!ParamValidator.isValidPostId(postID)) || (ParamValidator.isEmpty(newPostSummary))) {
      return updated;
    }
    Integer postId = Integer.parseInt(postID);
    Connection con = null;
    PreparedStatement ps = null;
    String updatePostContentSQL = "UPDATE posts "
                                 + "SET postSummary = ? "
                                + "WHERE postId = ?";
    try
    {
      con = getConnection();
      ps = con.prepareStatement("UPDATE posts SET postSummary = ? WHERE postId = ?");
      ps.setString(1, newPostSummary);
      ps.setInt(2, postId);
      ps.executeUpdate();
      updated = true;
    }
    catch (SQLException ex)
    {
      ex.printStackTrace();
      System.out.println("Couldn't Update Post Content");
    }
    finally
    {
      DBUtil.closeStatement(ps);
      DBUtil.closeConnection(con);
    }
    return updated;
  }
  
    
    
    
    
    
    public boolean deletePost(String postID){
        if(postID == null) return false;
        Integer postId = Integer.parseInt(postID);
        boolean canDelete = getSpecificPost(postID) != null;
        final String DELETESQL = "DELETE FROM posts WHERE postId = ?";
        boolean deleted = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = getConnection();
            ps = con.prepareStatement(DELETESQL);
            ps.setInt(1, postId);
        if(canDelete){    //check to make sure the post exists.
            int succeeded = ps.executeUpdate(); //run the delete code
            if(succeeded >= 1) deleted = true;
            System.out.println("deleted post from Database: " + postID);
              System.out.println("Now deleting:" + DirectoryCreator.BASEPATH + PostDirectoryCreator.POSTDIRECTORY + postId +" Directory");
                    DirectoryDeleter.deleteFolder(new File(DirectoryCreator.BASEPATH + PostDirectoryCreator.POSTDIRECTORY + postId));
        }
       
        }
        
        catch(SQLException ex){
            ex.printStackTrace();
            
        }
        
        finally{
            DBUtil.closeStatement(ps);
            DBUtil.closeConnection(con);
        }
        return deleted;
    }
    
    public List<RecipeStepBean> getRecipeStepBeanList(String postID){
        if(!ParamValidator.isValidPostId(postID)) return null;
        List<RecipeStepBean> stepList = null;
        Integer postId = Integer.parseInt(postID);
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        final String SQLSTRING = "SELECT stepId, parentId, stepNumber, stepSummary FROM recipe_steps " +
                                 "WHERE parentId = ? "+
                                 "ORDER BY stepNumber ASC";
        try{
            con = getConnection();
            ps = con.prepareStatement(SQLSTRING);
            ps.setInt(1, postId);
            RecipeStepBean stepBean;
            rs = ps.executeQuery();
            stepList = new ArrayList<>();
            while(rs.next()){
                stepBean = new RecipeStepBean(rs.getInt("stepId"), rs.getInt("parentId"), rs.getInt("stepNumber"), rs.getString("stepSummary"));
                stepList.add(stepBean);
            }
            return stepList;
        }
        catch(SQLException ex){
          ex.printStackTrace();
        }
        finally{
            DBUtil.closeStatement(ps);
            DBUtil.closeConnection(con);
            DBUtil.closeRS(rs);
           
        }
        return stepList;
    }
     
      
      public boolean insertRecipeSteps(String[] recipeSteps, String postID ){
          boolean success = false;
          if(!ParamValidator.isValidPostId(postID)) return success;
          if(recipeSteps == null) return success;
          Integer parentId = Integer.parseInt(postID);
          Connection con = null;
          PreparedStatement stmt = null;
          final String  INSERTSTRING = "INSERT into recipe_steps(parentId, stepNumber, stepSummary) "
                                        + "VALUES(?, ?, ?)";
          
          try{
              con = getConnection();
              stmt = con.prepareStatement(INSERTSTRING);
              
              for(int i = 0; i < recipeSteps.length; i++){
                  stmt.setInt(1, parentId);
                  stmt.setInt(2, i+1 );
                  stmt.setString(3, recipeSteps[i]);
                  stmt.addBatch();        //add each row
              }
              
              stmt.executeBatch();              //submit batch all at once.
              success = true;
          }
          
          catch(SQLException ex){
              success = false;
          }
          
          finally{
              DBUtil.closeConnection(con);
              DBUtil.closeStatement(stmt);
          }
         
          return success;
      }
      
        public boolean updateRecipeSteps(String[] recipeSteps, String postID)
  {
    boolean updated = false;
    if ((!ParamValidator.isValidPostId(postID)) || (recipeSteps == null)) {
      return updated;
    }
    Integer postId = Integer.parseInt(postID);
    Connection con = null;
    PreparedStatement delStatement = null;
    PreparedStatement insertStatement = null;
    String deleteQuery = "DELETE FROM baketrade.recipe_steps WHERE parentId = ?";
    
    String insertQuery = "Insert INTO recipe_steps(parentId, stepNumber, stepSummary) "
                            + "VALUES(?, ? ,?)";
    try
    {
      con = getConnection();    // use the same connection for both updates/queries
      con.setAutoCommit(false);
      delStatement = con.prepareStatement(deleteQuery);
      delStatement.setInt(1, postId);
      delStatement.execute();      //do the  delete first, and queue the execute
      
      insertStatement = con.prepareStatement(insertQuery);  //prepare the insert and use a batch on it
      for (int i = 0; i < recipeSteps.length; i++)
      {
        insertStatement.setInt(1, postId.intValue());
        insertStatement.setInt(2, i + 1);
        insertStatement.setString(3, recipeSteps[i]);
        insertStatement.addBatch();
      }
      insertStatement.executeBatch(); // queue the batch
      con.commit();        //send both statements/queries
      updated = true;
    }
    catch (SQLException ex)
    {
      System.out.println("Error on Delete or Insert of RecipeStep, rolling back.");
      DBUtil.rollback(con);
    }
    finally
    {
      DBUtil.closeStatement(delStatement);
      DBUtil.closeStatement(insertStatement);
      DBUtil.closeConnection(con);
    }
    return updated;
  }
      public boolean insertPostCategories(String postID, String[] catsToInsert){
          boolean inserted = false;
          if(!ParamValidator.isValidPostId(postID)) return inserted;
          Integer postId = Integer.parseInt(postID);
          Connection con = null;
          PreparedStatement stmt = null;
          String insertCategoriesSQL = "INSERT INTO post_categories(postId, categoryId) "
                  + "VALUES(?, ?)";
         try{
             con = getConnection();
             stmt = con.prepareStatement(insertCategoriesSQL);
             for(String categoryId : catsToInsert){
                 stmt.setInt(1, postId);
                 stmt.setInt(2, Integer.parseInt(categoryId));
                 stmt.addBatch();
             }
             stmt.executeBatch();
             inserted = true;
         }
         catch(SQLException ex){
             ex.printStackTrace();
             
         }
         finally{
             DBUtil.closeStatement(stmt);
             DBUtil.closeConnection(con);
         }
         return inserted;
          
      }
      
      public int getPostCount(){
          int postCount = 0;
          Connection con = null;
          Statement stmt = null;
          ResultSet rs = null;
          
          String countSQL = "SELECT COUNT(*) AS numOfPosts FROM posts";
          try{
              con = getConnection();
               stmt = con.createStatement();
              rs = stmt.executeQuery(countSQL);
              if(rs.next()){
                  postCount = rs.getInt("numOfPosts");
              }
              
          }
          catch(SQLException ex){
              System.out.println("Could not execute getPostCount");
          }
          finally{
              DBUtil.closeStatement(stmt);
              DBUtil.closeRS(rs);
              DBUtil.closeConnection(con);
          }
          return postCount;
      }
      
      public List<CategoryBean> getCategoryList(){
          List<CategoryBean> categoryList = new ArrayList<>();
          Connection con = null;
          PreparedStatement stmt = null;
          ResultSet rs = null;
          String categorySQL = "SELECT * FROM categories "+
                                "ORDER BY categoryName ASC";
          try{
              con = getConnection();
              stmt = con.prepareStatement(categorySQL);
              rs = stmt.executeQuery();
              
              while(rs.next()){
                  Integer categoryId = rs.getInt(1);
                  String categoryName = rs.getString(2);
                  CategoryBean catBean = new CategoryBean(categoryId, categoryName);
                  categoryList.add(catBean);
              }
          }
          catch(SQLException ex){
              ex.printStackTrace();
          }
          finally{
          DBUtil.closeStatement(stmt);  
          DBUtil.closeRS(rs);
          DBUtil.closeConnection(con);
          }
          
          return categoryList;
      }
      
      public List<CategoryBean> getSpecificPostCategoriesList(String postID){
          if(!ParamValidator.isValidPostId(postID)) return null;
          List<CategoryBean> categoryList = new ArrayList<>();
          Integer postId = Integer.parseInt(postID);
          Connection con = null;
          ResultSet rs = null;
          PreparedStatement ps = null;
          String getCategories = "Select categories.categoryId AS categoryId, categories.categoryName AS categoryName " + 
                                 "FROM post_categories "+
                                 "JOIN categories ON post_categories.categoryId = categories.categoryId "+
                                 "WHERE post_categories.postId = ?";
          try{
              con = this.getConnection();
              ps = con.prepareStatement(getCategories);
              ps.setInt(1, postId);
              rs = ps.executeQuery();
              
              while(rs.next()){
                 Integer catId = rs.getInt("categoryId");
                 String catName = rs.getString("categoryName");
                 CategoryBean categoryBean = new CategoryBean(catId, catName);
                 categoryList.add(categoryBean);
              }
          }
          
          catch(SQLException ex){
              ex.printStackTrace();
          }
          
          finally{
              DBUtil.closeStatement(ps);
              DBUtil.closeRS(rs);
              DBUtil.closeConnection(con);
              if(categoryList.size() == 0) categoryList = null;
          }
          return categoryList;
      }
      
      public List<Integer> getSearchResultsList(String params){
          String searchParams = params;

          List<Integer> postIds = new ArrayList<>();
          Connection con = null;
          PreparedStatement ps = null;
          ResultSet rs = null;
          String searchSQL = "SELECT returned.postId FROM "
                             + "(SELECT posts.postId, concat_ws(' ', posts.postTitle, posts.postSummary, group_concat(categories.categoryName)) AS search "
                                + "FROM posts "
                               + "JOIN post_categories ON posts.postId = post_categories.postId "
                               + "JOIN categories ON post_categories.categoryId = categories.categoryId "
                                + "GROUP BY postId) AS returned "
                                + "WHERE returned.search LIKE ?";
          try{
               String searchQuery = "%" + searchParams + "%";
              con = getConnection();
              ps = con.prepareStatement(searchSQL);
              ps.setString(1, searchQuery);
              rs = ps.executeQuery();
              while(rs.next()){
                  postIds.add(rs.getInt(1));
              }
         
              
              
          }
          catch(SQLException ex){
              ex.printStackTrace();
          }
          finally{
              DBUtil.closeConnection(con);
              DBUtil.closeStatement(ps);
              DBUtil.closeRS(rs);
          }
          return postIds;
      }
      
      private Connection getConnection()
  {
    Connection con = ConnectionFactory.returnConnection("pooled");
    return con;
  }
      
}
