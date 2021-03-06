package br.com.tairoroberto.qualimsolucoes;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

import br.com.tairoroberto.adapters.AdapterExpListview;
import br.com.tairoroberto.util.HttpConnection;

public class PerfilAlterarSenhaActivity extends ActionBarActivity{


    private DrawerLayout mDrawerLayout;
    private ExpandableListView mDrawerList_left;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mTelasTitles;
    private String answer;
    private SearchView searchView;
    UsuarioLogado usuarioLogado = new UsuarioLogado();
    private static final String PREF_NAME = "InicialActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        // Coloca um efeito antes de mostrar a tela principal
        overridePendingTransition(R.anim.push_up_exit,R.anim.push_left_exit);
        setContentView(R.layout.activity_perfil_trocar_senha);

        // mostra o logo do app na actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Home");


        //Verifica se foi enviado acão de sair
        Intent intent = getIntent();
        if (intent != null){

            Bundle bundle = intent.getExtras();
            if (bundle != null){
                usuarioLogado = bundle.getParcelable("usuarioLogado");
            }
        }

        /****************************************************************************************/
        /**                     Implementação do ExpadableListView                             */
        /**************************************************************************************/
        //mTitle = mDrawerTitle = getTitle();
        //Pega um array de String para colocar no drawer

        //Coloca os resources nas variáveis
        mTelasTitles = getResources().getStringArray(R.array.telas_array);

        //Linka o DrawerLayout do java com o xml
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Linka o ListView do java com o xml
        mDrawerList_left = (ExpandableListView) findViewById(R.id.left_drawer);

        //  Configura uma sombra personalizada quando o Drawer é aberto
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        //Configura a lista do Drawer com os items do array e seta o evento de clique da lista
        //configura a lista da direita e esqueda do Drawer
        mDrawerList_left.setAdapter(new AdapterExpListview(this));

        mDrawerList_left.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //Verifify whacth screen go
                if (parent.getExpandableListAdapter().getChild(groupPosition,childPosition).toString() == "Tela Inicial"){
                    selectItemLeft(0);
                }else if (parent.getExpandableListAdapter().getChild(groupPosition,childPosition).toString() == "Visualizar tarefas"){
                    selectItemLeft(1);
                }else if (parent.getExpandableListAdapter().getChild(groupPosition,childPosition).toString() == "Cadastrar cronograma"){
                    selectItemLeft(2);
                }else if (parent.getExpandableListAdapter().getChild(groupPosition,childPosition).toString() == "Visualizar cronograma"){
                    selectItemLeft(3);
                }else if (parent.getExpandableListAdapter().getChild(groupPosition,childPosition).toString() == "Cadastrar visitas técnicas"){
                    selectItemLeft(4);
                }else if (parent.getExpandableListAdapter().getChild(groupPosition,childPosition).toString() == "Visualizar visitas técnicas"){
                    selectItemLeft(5);
                }else if (parent.getExpandableListAdapter().getChild(groupPosition,childPosition).toString() == "Cadastrar auditórias"){
                    selectItemLeft(6);
                }else if (parent.getExpandableListAdapter().getChild(groupPosition,childPosition).toString() == "Visualizar auditórias"){
                    selectItemLeft(7);
                }else if (parent.getExpandableListAdapter().getChild(groupPosition,childPosition).toString() == "Cadastrar check list"){
                    selectItemLeft(8);
                }else if (parent.getExpandableListAdapter().getChild(groupPosition,childPosition).toString() == "Visualizar check list"){
                    selectItemLeft(9);
                }else if (parent.getExpandableListAdapter().getChild(groupPosition,childPosition).toString() == "Insirir despesa"){
                    selectItemLeft(10);
                }else if (parent.getExpandableListAdapter().getChild(groupPosition,childPosition).toString() == "Ver despesas"){
                    selectItemLeft(11);
                }else if (parent.getExpandableListAdapter().getChild(groupPosition,childPosition).toString() == "Mudar foto"){
                    selectItemLeft(12);
                }else if (parent.getExpandableListAdapter().getChild(groupPosition,childPosition).toString() == "Foto de assinatura"){
                    selectItemLeft(13);
                }else if (parent.getExpandableListAdapter().getChild(groupPosition,childPosition).toString() == "Trocar senha"){
                    selectItemLeft(14);
                }
                return false;
            }
        });

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                PerfilAlterarSenhaActivity.this,    /* Classe que chama a activity Activity */
                mDrawerLayout,         /* Layout que será mostrado DrawerLayout  */
                R.drawable.ic_drawer,  /* Icone que aparecera na ActionBar */
                R.string.drawer_open){ /* Descrição */

            // Método chamado quando o fragment é fechado
            public void onDrawerClosed(View view) {
                //Toast.makeText(EventosActivity.this, "Teste Drawer close",Toast.LENGTH_SHORT).show();
            }

            // Método chamado quando o fragment é aberto
            public void onDrawerOpened(View drawerView) {
                //Toast.makeText(EventosActivity.this, "Teste Drawer open",Toast.LENGTH_SHORT).show();
            }

        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            //selectItemLeft(0);
        }

    }

    /****************************************************************************************/
    /**                               Implementação do Menu                                */
    /**************************************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);

        //Implementa o SearchView
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchFiltro());
        return super.onCreateOptionsMenu(menu);

    }

    /****************************************************************************************/
    /**                    Implementação da clase do searchView                            */
    /**************************************************************************************/
    private class SearchFiltro implements OnQueryTextListener{

        @Override
        public boolean onQueryTextChange(String query) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean onQueryTextSubmit(String query) {

            //Cria uma intent para manipular o websearch para o item selecionado
            //Envia conteudo do searchView capturado para uma intent para fazer uma busca na internet
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY,query);

            // catch event that there's no activity to handle intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(PerfilAlterarSenhaActivity.this, R.string.app_not_available, Toast.LENGTH_LONG).show();
            }
            return false;
        }
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Se o drawer for aberto, esconde os items de ações  relatados para o cotainer
        boolean drawerLeftOpen = mDrawerLayout.isDrawerOpen(mDrawerList_left);
        menu.findItem(R.id.action_exit).setVisible(!drawerLeftOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /****************************************************************************************/
    /**                      Implementação da selecção do menu                             */
    /**************************************************************************************/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Manipula as ações dos botões
        switch(item.getItemId()) {
            case R.id.action_exit:
                sendLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * @param position
     */
    /****************************************************************************************/
    /**             Implementação do seleção da lista de menus lateral                     */
    /**************************************************************************************/
    private void selectItemLeft(int position) {

        //Atualiza o item selecionado e titulo, depois fecha o Drawer
        mDrawerList_left.setItemChecked(position, true);
        //setTitle(mDrawerList_left.getExpandableListAdapter().getGroup(position).toString());
        mDrawerLayout.closeDrawer(mDrawerList_left);

        if (position == 0) {

            Intent intent = new Intent(PerfilAlterarSenhaActivity.this,PrincipalActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("usuarioLogado",usuarioLogado);
            startActivity(intent);

        } else if (position == 1) {
            Intent intent = new Intent(PerfilAlterarSenhaActivity.this,TarefasVisualizarActivity.class);
            intent.putExtra("usuarioLogado",usuarioLogado);
            startActivity(intent);

        }else if (position == 2) {
            Intent intent = new Intent(PerfilAlterarSenhaActivity.this,CronogramaInserirActivity.class);
            intent.putExtra("usuarioLogado",usuarioLogado);
            startActivity(intent);

        }else if (position == 3) {
            Intent intent = new Intent(PerfilAlterarSenhaActivity.this,CronogramaVerActivity.class);
            intent.putExtra("usuarioLogado",usuarioLogado);
            startActivity(intent);

        } else if (position == 4) {
            Intent intent = new Intent(PerfilAlterarSenhaActivity.this,RelatoriosCadastVisitaTecActivity.class);
            intent.putExtra("usuarioLogado",usuarioLogado);
            startActivity(intent);

        }else if (position == 5) {
            Intent intent = new Intent(PerfilAlterarSenhaActivity.this,RelatoriosVisualVisitaTecActivity.class);
            intent.putExtra("usuarioLogado",usuarioLogado);
            startActivity(intent);

        }else if (position == 6) {
            Intent intent = new Intent(PerfilAlterarSenhaActivity.this,RelatoriosCadastAuditoriaActivity.class);
            intent.putExtra("usuarioLogado",usuarioLogado);
            startActivity(intent);

        }else if (position == 7) {
            Intent intent = new Intent(PerfilAlterarSenhaActivity.this,RelatoriosVisualAuditoriaActivity.class);
            intent.putExtra("usuarioLogado",usuarioLogado);
            startActivity(intent);

        }else if (position == 8) {
            Intent intent = new Intent(PerfilAlterarSenhaActivity.this,RelatoriosCadastCheckListActivity.class);
            intent.putExtra("usuarioLogado",usuarioLogado);
            startActivity(intent);

        }else if (position == 9) {
            Intent intent = new Intent(PerfilAlterarSenhaActivity.this,RelatoriosVisualCheckListActivity.class);
            intent.putExtra("usuarioLogado",usuarioLogado);
            startActivity(intent);

        }else if (position == 10) {
            Intent intent = new Intent(PerfilAlterarSenhaActivity.this,PrestacaoContasInserirActivity.class);
            intent.putExtra("usuarioLogado",usuarioLogado);
            startActivity(intent);

        }else if (position == 11) {
            Intent intent = new Intent(PerfilAlterarSenhaActivity.this,PrestacaoContasVerActivity.class);
            intent.putExtra("usuarioLogado",usuarioLogado);
            startActivity(intent);

        }else if (position == 12) {
            Intent intent = new Intent(PerfilAlterarSenhaActivity.this,PerfilAlterarFotoActivity.class);
            intent.putExtra("usuarioLogado",usuarioLogado);
            startActivity(intent);

        }else if (position == 13) {
            Intent intent = new Intent(PerfilAlterarSenhaActivity.this,PerfilAlterarAssinaturaActivity.class);
            intent.putExtra("usuarioLogado",usuarioLogado);
            startActivity(intent);

        }else if (position == 14) {
            /*Intent intent = new Intent(PerfilAlterarSenhaActivity.this,PerfilAlterarSenhaActivity.class);
            intent.putExtra("usuarioLogado",usuarioLogado);
            startActivity(intent);*/
        }

    }



    /****************************************************************************************/
    /**                          Muda o titulo da ActionBar                                */
    /**************************************************************************************/
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Syncroniza o toggle state depois  que o onRestoreInstanceState tiver ocorrido.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /*******************************************************************************************/
    /**                  Method to send Password to system                                    */
    /*****************************************************************************************/

    public void sendServer(View view){
        EditText senha = (EditText)findViewById(R.id.edtSenha);
        EditText senhaConfirmation = (EditText)findViewById(R.id.edtSenhaComfirma);
        ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
        valores.add(new BasicNameValuePair("nutricionista_id", usuarioLogado.getId()+""));
        valores.add(new BasicNameValuePair("senha", senha.getText().toString()));
        valores.add(new BasicNameValuePair("senha_confirmation",senhaConfirmation.getText().toString()));

        if (senha.getText().toString().equals("") || senhaConfirmation.getText().toString().equals("")){
            Toast.makeText(this, "Insira uma senha...!!!", Toast.LENGTH_LONG).show();
        }else if (!senha.getText().toString().equals(senhaConfirmation.getText().toString())){
            Toast.makeText(this, "As senhas devem ser iguais...!", Toast.LENGTH_LONG).show();
        }else{
            ChangePassword changePassword = new ChangePassword(this);
            changePassword.execute(valores);
        }
    }


    /*******************************************************************************************/
    /**                   Class to make insert event in system                               */
    /*****************************************************************************************/
    private class ChangePassword extends AsyncTask<ArrayList<NameValuePair>,Void,String> {
        Context context;
        private ProgressDialog progress;

        public ChangePassword(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(context);
            progress.setMessage("Alterando senha...");
            progress.show();
        }

        @Override
        protected String doInBackground(ArrayList<NameValuePair>... params) {
            final String url = "http://www.nowsolucoes.com.br/qualim/public/change-password-android";
            answer = HttpConnection.getSetDataWeb(url, params[0]);
            return answer;
        }

        @Override
        protected void onPostExecute(String answer) {
            super.onPostExecute(answer);
            //Log.i("Script","Resposta do servidor: "+answer);
            progress.dismiss();
            if (answer.equals("Saved")){
                //store login and password in sharedPreferences
                /** In this mode, we can acess this sharedpreference in all Activities and Fragments*/
                SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(context, "Senha Alterada com sucesso...!!!", Toast.LENGTH_LONG).show();
                Log.i("Script","Resposta servidor: "+answer);

            }else{
                Toast.makeText(context, "Senha não Alterada..!!!", Toast.LENGTH_LONG).show();
                Log.i("Script","Resposta servidor: "+answer);
            }
        }
    }


    /*******************************************************************************************/
    /**                  Method to make logout in system                                      */
    /*****************************************************************************************/
    public void sendLogout(){
        final ProgressDialog progress = new ProgressDialog(PerfilAlterarSenhaActivity.this);
        progress.setMessage("Desconectando...");
        progress.show();

        final String url = "http://www.nowsolucoes.com.br/qualim/public/logout-android";

        new Thread(){
            public void run(){

                ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
                valores.add(new BasicNameValuePair("acao", "desconectar"));

                answer = HttpConnection.getSetDataWeb(url, valores);

                runOnUiThread(new Runnable(){
                    public void run(){
                        try{
                            if (answer != null){
                                if (!answer.equals("Ainda conectado")){

                                    PerfilAlterarSenhaActivity.this.finish();
                                    progress.dismiss();
                                    Log.i("Script","Resposta:" + answer);

                                }else{
                                    progress.dismiss();
                                    Toast.makeText(PerfilAlterarSenhaActivity.this, "Ainda conectado..!!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        catch(NumberFormatException e){ e.printStackTrace(); }
                    }
                });
            }
        }.start();
    }

}
