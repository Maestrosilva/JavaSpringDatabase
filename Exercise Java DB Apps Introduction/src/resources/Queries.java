package resources;

public class Queries {
    public static final String GET_VILLAIN_NAMES_AND_NUMBER_OF_MINIONS_BY_MAXIMUM_MINION_COUNT =
            """
                    select v.name, count(distinct minion_id) as number_of_minions from villains v
                    left join minions_villains mv on v.id = mv.villain_id
                    group by v.id
                    having number_of_minions > ?
                    order by number_of_minions desc;""";
    public static final String GET_NUMBER_OF_MINIONS_BY_VILLAIN_ID =
            """
                    select count(minion_id) as minion_count from minions_villains
                    group by villain_id
                    having villain_id = ?;""";
    public static final String GET_MINION_NAMES_AND_MINION_AGE =
            """
                    select m.name, m.age from minions m
                    left join minions_villains mv on m.id = mv.minion_id
                    where villain_id = ?;""";
    public static final String GET_VILLAIN_NAME_BY_ID =
            """
                    select v.name from villains v
                    where v.id = ?;""";
    public static final String GET_VILLAIN_ID_BY_NAME =
            """
                    select v.id from villains v
                    where v.name = ?;""";
    public static final String GET_MINION_ID_BY_NAME =
            """
                    select m.id from minions m
                    where m.name = ?;""";
    public static final String GET_TOWN_ID_BY_NAME =
            """
                    select t.id from towns t
                    where t.name = ?;""";
    public static final String ADD_TOWN =
            "insert into towns (name) value(?);";
    public static final String ADD_VILLAIN =
            "insert into villains (name, evilness_factor) value (?, ?);";
    public static final String ADD_MINION =
            "insert into minions (name, age, town_id) values (?, ?, ?);";
    public static final String CONNECT_MINIONS_AND_VILLAINS =
            "insert into minions_villains (minion_id, villain_id) VALUES (?, ?);";
    public static final String CHECK_IF_CONNECTION_EXISTS =
            """
                    select mv.villain from minions_villains mv
                    where minion_id = ?;""";
    public static final String CHANGE_TOWN_NAMES_TO_UPPERCASE =
            """
                    update towns t set t.name = upper(t.name)
                    where country = ?;""";
    public static final String GET_TOWN_BY_COUNTRY =
            """
                    select t.name from towns t
                    where t.country = ?;""";
    public static final String DELETE_VILLAIN_BY_ID =
            "delete from villains where id = ?;";
    public static final String RELEASE_MINIONS_OF_VILLAIN_WITH_ID =
            """
                    update minions_villains mv\s
                    set mv.villain_id = null\s
                    where villain_id = ?;""";
    public static final String GET_ALL_MINION_NAMES =
            "select m.name from minions m;";
    public static final String GET_ALL_MINION_NAMES_AND_AGES =
            "select m.name, m.age from minions m;";
    public static final String INCREASE_MINION_AGE =
            """
                    update minions set age = age + ?
                    where id = ?;
                    """;
    public static final String DROP_PROCEDURE =
            "drop procedure if exists usp_get_older";
    public static final String CREATE_PROCEDURE =
            """
                    create procedure usp_get_older(minion_id int)
                    begin
                        start transaction;
                        update minions set age = age + 1
                        where id = minion_id;
                    end""";
    public static final String EXECUTE_PROCEDURE =
            "{call usp_get_older(?)}";
}