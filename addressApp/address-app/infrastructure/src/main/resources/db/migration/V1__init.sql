create table target (id UUID PRIMARY KEY , firstname text, name text);

create table town(id uuid PRIMARY KEY, postCode NUMERIC, name text);

create table address(id uuid PRIMARY KEY , number NUMERIC, street text, town_id uuid REFERENCES town(id));

create table targetAddress (
  target_id uuid references target(id),

  address_id uuid REFERENCES address(id), PRIMARY KEY(target_id, address_id));

