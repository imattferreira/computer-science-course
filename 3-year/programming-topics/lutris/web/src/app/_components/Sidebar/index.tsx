import List from "./List";

function Sidebar() {
  return (
    <aside className="border-r border-r-zinc-600 min-h-[calc(100vh_-_64px)] px-4 py-4 w-60">
      <List />
    </aside>
  );
}

export default Sidebar;
