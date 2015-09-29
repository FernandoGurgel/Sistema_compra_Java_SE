/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Connect.ConnectFactory;
import java.util.ArrayList;
import java.util.Collection; // teste
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import model.Produtos;

/**
 *
 * @author fernando
 */
public class ProdutoDao {
    
    public ConnectFactory connectFactory;
   
   
    
    public int SalavarDao(Produtos produtos) {

        try {
            
            connectFactory = new ConnectFactory();
            
            connectFactory.conectar();

            String sql = "insert into produtos (nome, valor) values("
                    + "'" + produtos.getNome() + "',"
                    + "'" + produtos.getValor() + "');";
            
            return connectFactory.insertSQL(sql);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            connectFactory.fecharConexao();
        }
    }

    public Produtos trazerCod(int cod) {
        
        Produtos produtos = new Produtos();
        
        connectFactory = new ConnectFactory();
        try {
            connectFactory.conectar();
            
            String sql = "select nome, valor from produtos where cod = '"+ cod+"';";
            
            connectFactory.executarSQL(sql);
            
            while(connectFactory.getResultSet().next()){
                produtos.setNome(connectFactory.getResultSet().getString(1));
                produtos.setValor(connectFactory.getResultSet().getDouble(2));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            connectFactory.fecharConexao();
        }
        return produtos;
    }
    
    public List<Produtos> ListaProduto(){
        
        connectFactory = new ConnectFactory();
        Produtos produto = new Produtos();
        
        ArrayList<Produtos> listaProdutos = new ArrayList();
        
        try {
                    
            //Comando para o banco
            connectFactory.conectar();
            String sql = "select * from produtos;";
            connectFactory.executarSQL(sql);
            connectFactory.setResultSet(connectFactory.getStatement().executeQuery(sql));
            
            while(connectFactory.getResultSet().next()){
                
                listaProdutos.add(new Produtos(connectFactory.getResultSet().getInt(1), connectFactory.getResultSet().getString(2), connectFactory.getResultSet().getDouble(3)));
                        
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            connectFactory.fecharConexao();
        }
        return listaProdutos;
       
    }
    
    public boolean excluirProduto(int codigoProduto){
        
        connectFactory = new ConnectFactory();
               
        try{
            
            connectFactory.conectar();
            
            String sql = "DELETE FROM produtos WHERE cod = '" + codigoProduto + "';";
            
            connectFactory.executarUpdateDeleteSQL(sql);
            
            return true;
            
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            connectFactory.fecharConexao();
        }
        
    }
    
    public static void main(String[] args) {
        
        ProdutoDao tc = new ProdutoDao();
       
        
               
        
    }

    
  
}
