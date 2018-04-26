/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.servidor;

import br.com.telemetriaAmonia.dao.*;
import br.com.telemetriaAmonia.model.Dispositivo;
import br.com.telemetriaAmonia.model.ModuloGSM;
import br.com.telemetriaAmonia.model.RegistroConcentracaoAmonia;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;

/**
 *
 * @author Jean Luiz
 */
//classe responsável por receber os dados do dispositivo/GSM e inseri-los na base de dados da aplicação
//OBS: comunicação via socket multi threads
public class ServidorTCP {
    public static void main(String[] args) throws IOException {
        System.out.println("Socket Server TCP Rodando...\n");
        //armazena número da porta
        int numeroPorta = 4550;
        //servidor socket
        ServerSocket servidor = null;
        //cliente socket
        Socket cliente = null;
        try {
            //instancia o server socket ouvindo na porta disponível;
            servidor = new ServerSocket(numeroPorta);
            System.out.print("Servidor ouvindo na porta: <"+numeroPorta+">\n");
            while(true) {
                //o método acept() bloqueia a execução até que o servidor receba um pedido de execução
                cliente = servidor.accept();
                System.out.println("Cliente conectado: <"+cliente.getInetAddress().getHostAddress()+">\n");
                //inicia a Thread do Cliente
                new ThreadTrataCliente(cliente).start();
            }
        } catch (Exception e) {
            //imprimindo notificação no terminal em caso de erro
            System.out.println("Erro: "+e.getMessage());
            //System.out.println("Problema ao criar ou receber o socket!!");
        } finally {
            servidor.close();
        }
    }   
    //Thread para tratar as conexões recebidas pelo servidor
    private static class ThreadTrataCliente extends Thread {
        private Socket cliente = null;
        private BufferedReader input = null;
        private DataOutputStream output = null;
        //armazena dados
        String dados = "";
        //armazena dados separados
        String[] separaDados = null;
        //variaveis armazena dados após separados(finalidade: inserir no BD)
        int idGSM = 0;
        String creditos = "";
        int idDisp = 0;
        int nh3 = 0;
        Date data = null;
        Time hora = null;
        int CSQ = 0;
        //variavel que armazena data formatada para tipo Date
        DateFormat dataFormatada = null;
        
        
        //metodo conecta
        public ThreadTrataCliente(Socket cliente) {
            this.cliente = cliente;
            try {
                //cria um buffer que irá armazenar as informações enviadas pelo cliente
                input = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                //cria uma stream de saída para retorno das informações ao cliente
                //output = new DataOutputStream(cliente.getOutputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        //metodo que executa a thred
        public void run() {
            try {
                //executa tarefa da conexao estabelecida
                executaRequisicao();
                //libera os recursos
                fechaConexao();
            } catch (Exception e) {
                System.out.println("Excessão ocorrida na Thread: "+e.getMessage());
                try {
                    cliente.close();
                } catch (Exception ec) {                
                    System.out.println("Excessão, fechar conexão cliente: "+ec.getMessage());
                }
            }               
        }
        
        //metodo responsavel por executar requisição do cliente(extrai e insere dados no banco de dados)
        private void executaRequisicao() {
            String buscaData = new BuscarDataSistema().getDataSistema();
            InterfaceModuloGSM gsmDAO = new ModuloGSMDAO();
            InterfaceDispositivo dispDAO = new DispositivoDAO();
            InterfaceRegistroConcentracaoAmonia regDAO = new RegistroConcentracaoAmoniaDAO();
            
            try {                                
                System.out.println("\nRecebendo dados...");
                //faz a leitura das informações e armazena na variavel dados
                dados = input.readLine().toString();                       
                System.out.println("\nPacote recebido: "+dados+" recebida pelo cliente: "+cliente.getInetAddress().getHostAddress());
                //separa dados recebidos e armazena no vetor
                separaDados = dados.split("&");   
                
                //basca data e hora do sistema (servidor)                 
                Timestamp ti = Timestamp.valueOf(buscaData);
                
                //verifica pacote de dados recebidos(se vetor = 2 posições)
                if(separaDados.length == 2) {
                    //dados recebidos = ID módulo GSM, creditos 
                    idGSM = Integer.parseInt(separaDados[0]);
                    creditos = separaDados[1];                  //armazena String creditos
                    System.out.println("\nDados > ID Módulo GSM: "+idGSM+" Creditos: "+creditos);
                    
                    //instanciando o objeto módulo GSM
                    ModuloGSM gsmObj = gsmDAO.getGSM(idGSM);
                    
                    //----------procedimento para atualizar dados do GSM na base de dados----                    
                    //(verifica se existe o GSM)busca o módulo GSM no banco de dados (pelo ID)                    
                    System.out.println("\nRetorno para busca por modulo GSM: "+gsmObj.getNome_gsm());
                    //(se existe)atualiza valor do crédito
                    if(gsmObj != null) {
                        gsmObj.setCredito(creditos);                                
                        gsmObj.setModificado(ti);
                        gsmDAO.atualizarGSM(gsmObj);                        
                        System.out.println("\nCréditos atualizados em Modulo GSM: "+gsmObj.getCredito()+" "+gsmObj.getModificado());
                        //output.writeBytes("Dados recebidos e atualizados com sucesso pelo servidor: "+creditos+" "+buscaData); 
                        //output.flush();
                    } else {
                        System.out.println("\nMódulo GSm não identificado (não existe)!!");
                    }
                    //-----------------------------------------------------------------------                                      
                } else if(separaDados.length == 6) { //verifica pacote de dados(se vetor = 5 posições)                    
                                         
                    idGSM = Integer.parseInt(separaDados[0]);                    
                    idDisp = Integer.parseInt(separaDados[1]);                     
                    //dados recebidos = ID disp, nh3, data, hora, csq
                    nh3 = Integer.parseInt(separaDados[2]);                         //armazena int nh3
                    data = Date.valueOf(separaDados[3]);                            //armazena Date data
                    hora = Time.valueOf(separaDados[4]);                            //armazena Time hora
                    CSQ = Integer.parseInt(separaDados[5]);                         //armazena int CSQ
                    Timestamp dataHora = Timestamp.valueOf(separaDados[3]+" "+separaDados[4]); //armazena Datetime
                    System.out.println("Dados > ID GSM: "+idGSM+" ID Disp: "+idDisp+" NH³: "+nh3+" Data: "+data+" Hora: "+hora+" CSQ: "+CSQ+" DataHora:"+dataHora);
                                          
                    //instanciando os objetos para verificação e atualização
                    Dispositivo dispObj = dispDAO.getDispositivo(idDisp);
                    RegistroConcentracaoAmonia regObj = regDAO.getUltimoRegistroByDisp(idDisp);            
                    ModuloGSM gsmObj = gsmDAO.getGSM(idGSM);
                    
                    //-------------procedimento para atualizar dados de NH3 na base de dados---
                    //atualiza o campo dataHoraConexao em dispositivo
                    if(dispObj != null) {                        
                        dispObj.setConexao(ti);
                        dispObj.setModificado(ti);
                        dispDAO.atualizarDisp(dispObj);
                        System.out.println("\nConexão atualizada em dispositivo: "+dispObj.getConexao());                    
                    }   
                    //atualiza o campo CSQ em módulo GSM
                    if(gsmObj != null) {
                        gsmObj.setCsq(CSQ);                           
                        gsmObj.setModificado(ti);
                        gsmDAO.atualizarGSM(gsmObj);
                        System.out.println("\nCSQ atualizado em modulo GSM: "+gsmObj.getCsq());                    
                    } 
                    //verifica se encontrou algum registro de NH3 para o dispositivo
                    if(regObj != null) {
                        //se existe registro pra data recebida apenas atualiza
                        if(data.compareTo(regObj.getData_reg()) == 0) {
                            //se concentração maior for menor que atual, atualiza
                            if(regObj.getConc_maior() < nh3) {
                                regObj.setConc_maior(nh3);
                                regObj.setData_hora_cma(dataHora);
                            }
                            //se concentração menor for maior que a atual, atualiza
                            if(regObj.getConc_menor() > nh3) {
                                regObj.setConc_menor(nh3);
                                regObj.setData_hora_cme(dataHora);
                            }
                            regObj.setConc_medida_reg(nh3);
                            regObj.setData_reg(data);
                            regObj.setHora_reg(hora);
                            regDAO.atualizarRegistro(regObj);  
                            //refresh aaqui
                            System.out.println("\nRegistro de amonia atualizado para o dispositivo: "+dispObj.getId_dis());
                        //se não insere um novo registro
                        } else {
                            regObj.setId_dis(dispObj);
                            regObj.setConc_medida_reg(nh3);
                            regObj.setData_reg(data);
                            regObj.setHora_reg(hora);
                            regObj.setConc_maior(nh3);
                            regObj.setData_hora_cma(dataHora);
                            regObj.setConc_menor(nh3);
                            regObj.setData_hora_cme(dataHora);
                            regDAO.salvarRegistro(regObj);
                            //refresh aqui
                            System.out.println("\nNova data. Registro de amonia inserido para o dispositivo: "+dispObj.getId_dis());
                        }
                    } else {
                        //se nada existe, instancia novo objeto e seta os valorespara armazenar
                        regObj = new RegistroConcentracaoAmonia();                                
                        regObj.setId_dis(dispObj);
                        regObj.setData_reg(data);
                        regObj.setHora_reg(hora);
                        regObj.setConc_medida_reg(nh3);
                        regObj.setConc_menor(nh3);                            
                        regObj.setConc_maior(nh3);
                        regObj.setData_hora_cme(dataHora);                            
                        regObj.setData_hora_cma(dataHora);
                        regDAO.salvarRegistro(regObj);
                        System.out.println("\nPrimeiro registro do dispositivo: "+regObj.getId_dis().getId_dis()+". Dados inseridos com sucesso!");
                    }                        
                    //output.writeBytes("Dados recebidos com sucesso pelo servidor: "+dados); 
                    //output.flush();
                    //-------------------------------------------------------------------------                    
                } else {
                    System.out.println("Erro na recepção dos dados, Buffer inconsistente. Conteúdo: "+separaDados+" Tamanho do buffer recebido: "+separaDados.length);
                    //output.writeBytes("Erro na recepção pelo servidor. Motivo buffer inconsistente."); 
                    //output.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }               
        }
        
        //metodo fecha conexão
        private void fechaConexao() {
            try {
                //fim da transferencia
                input.close();                
                //fim da transferencia
                //output.close();
                //fim da conexão
                cliente.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
