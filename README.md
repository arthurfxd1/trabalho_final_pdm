# GeoTasker - Gerenciador de Tarefas Baseado em Localização

O GeoTasker é um gerenciador de tarefas baseado em localização. O app permite q o usuário crie tarefas q vão disparar qnd o dispositivo entre no endereço digitado. Desenvolvido nativamente em Android (Kotlin).

## Recursos Nativos Implementados

1. GPS (Geofencing e Geocoding): Usa Geocoding pra converter endereços de texto em coordenadas geográficas reais. Através da API de Geofencing, monitora a posição em segundo plano e detecta a entrada em cercas virtuais de 1km ao redor do destino.

2. Notificações Locais (Push): Implementação de canais de notificação pra disparar alertas visuais e sonoros. Avisa o usuário sobre a tarefa no momento exato da chegada ao local, msm com o app fechado.

3. WebView: Painel de produtividade interno q gera conteúdo HTML dinamicamente. O dashboard consome dados do banco local (Room) pra exibir a qntd exata de tarefas ativas monitoradas.

## Instruções pra Teste

1. Sincronização: Abra o projeto no Android Studio e realize o "Sync Project with Gradle Files". Execute o app em um emulador q possua Google Play Services.

2. Permissões: Ao abrir o app, aceite as permissões de Localização e Notificações. Pro funcionamento pleno do GPS, acesse as configurações do Android e defina a permissão de localização do GeoTasker como "Permitir o tempo todo".

3. Validação de Notificações: Utilize o botão "Testar notificação agora" pra validar q o sistema de avisos push está configurado corretamente.

4. Validação de WebView: Clique em "Ver painel web" pra carregar o dashboard dinâmico. O painel deve refletir o número total de tarefas salvas no BD.

5. Simulação de GPS:
- Crie uma tarefa informando um endereço.
- No menu lateral do emulador (Extended Controls), acesse a aba Location.
- Digite as coordenadas ou pesquise o endereço no mapa do emulador e clique em "Set Location".

6. Limpeza de Dados: Utilize o botão "Limpar todas as tarefas" pra zerar o BD Room.

## Observações sobre a Simulação (Erro de Geofence)

Durante o desenvolvimento, observou-se q o emulador Android pode apresentar atrasos ou falhas ao disparar eventos de Geofencing qnd o usuário é "teletransportado" (Set Location) entre distâncias mto grandes rapidamente. O adroid prioriza a economia de energia e n processa a transição de entrada na cerca virtual imediatamente.

Pra comprovar q a lógica interna do app está correta, foram implementados os botões de teste manual de notificações e a contagem dinâmica no WebView, q validam a integração dos recursos nativos e o acesso ao BD, independente da oscilação do sinal de GPS do emulador.
