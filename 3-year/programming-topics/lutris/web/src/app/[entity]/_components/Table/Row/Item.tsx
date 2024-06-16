interface ItemProps {
  children: React.ReactNode;
}

function Item({ children }: ItemProps) {
  return <td className="text-center text-gray-300 text-sm py-3">{children}</td>;
}

export default Item;
