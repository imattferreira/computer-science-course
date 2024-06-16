interface ItemProps {
  children: React.ReactNode;
}

function Item({ children }: ItemProps) {
  return <th className="py-3 text-gray-200 text-base">{children}</th>;
}

export default Item;
