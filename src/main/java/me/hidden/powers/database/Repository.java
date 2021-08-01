package me.hidden.powers.database;

public interface Repository<T, PK> {
    void create(T model);
    void update(T model);
    void delete(PK key);
    void get(PK key);
    void all();
}
