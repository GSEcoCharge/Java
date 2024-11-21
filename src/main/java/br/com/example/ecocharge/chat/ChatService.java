// package br.com.example.ecocharge.chat;

// import org.springframework.ai.chat.client.ChatClient;
// import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
// import org.springframework.ai.chat.memory.InMemoryChatMemory;
// import org.springframework.stereotype.Service;

// import br.com.example.ecocharge.model.Usuario;

// @Service
// public class ChatService {
//     final ChatClient chatClient;

//     public ChatService(ChatClient.Builder chatClientBuilder) {
//         this.chatClient = chatClientBuilder
//                 .defaultSystem("""
//                         crie mensagens de boas vindas para serem enviadas por email paara os novos usuários do sistema EcoCharge.
//                         Usuario: <nome>, Email: <email>, Criacao: <criacao>, Ultima Localizacao: <localizacao>
//                         Mensagem de boas vindas para o cliente exibindo o nome e o email do usuário com a data da criação da conta e a localização informada.
//                         Exemplo: "Bem-vindo ao EcoCharge! Aqui você pode encontrar informações sobre pontos de recarga para veículos elétricos."
//                         """)
//                 .defaultAdvisors(
//                         new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
//                         .build();
//     }

//     public String sentToAi(Usuario usuario) {
//         String prompt = String.format("Usuario: %s, Email: %s, Criacao: %s, Ultima Localizacao: %s",
//         usuario.getNome(), usuario.getEmail(), usuario.getCriacao(), usuario.getLocalizacao());
//         String script = chatClient.prompt().user(prompt).call().content();
//         return script;
//     }

// }
