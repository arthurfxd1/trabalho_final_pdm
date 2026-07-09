package com.example.trabalho_final_pdm;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\rH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0012\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0013"}, d2 = {"Lcom/example/trabalho_final_pdm/tarefadao;", "", "atualizar", "", "t", "Lcom/example/trabalho_final_pdm/tarefa;", "(Lcom/example/trabalho_final_pdm/tarefa;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buscar_por_id", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buscar_todas", "Landroidx/lifecycle/LiveData;", "", "buscar_todas_lista", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletar", "inserir", "limpar_tudo", "app_debug"})
@androidx.room.Dao()
public abstract interface tarefadao {
    
    @androidx.room.Query(value = "SELECT * FROM tarefas ORDER BY id DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.trabalho_final_pdm.tarefa>> buscar_todas();
    
    @androidx.room.Query(value = "SELECT * FROM tarefas")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object buscar_todas_lista(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.trabalho_final_pdm.tarefa>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM tarefas WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object buscar_por_id(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.trabalho_final_pdm.tarefa> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object inserir(@org.jetbrains.annotations.NotNull()
    com.example.trabalho_final_pdm.tarefa t, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object atualizar(@org.jetbrains.annotations.NotNull()
    com.example.trabalho_final_pdm.tarefa t, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deletar(@org.jetbrains.annotations.NotNull()
    com.example.trabalho_final_pdm.tarefa t, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM tarefas")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object limpar_tudo(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}