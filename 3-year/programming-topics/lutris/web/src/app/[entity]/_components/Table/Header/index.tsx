import Item from "./Item";

interface HeaderProps {
  columns: string[];
}

function Header({ columns }: HeaderProps) {
  return (
    <thead>
      <tr>
        {columns.map((column) => (
          <Item key={column}>{column}</Item>
        ))}
        <Item>Ações</Item>
      </tr>
    </thead>
  );
}

export default Header;
