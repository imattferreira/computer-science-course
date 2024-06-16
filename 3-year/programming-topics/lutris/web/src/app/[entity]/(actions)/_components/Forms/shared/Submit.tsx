interface SubmitProps {
  type: "create" | "update";
}

function Submit({ type }: SubmitProps) {
  return (
    <button className="px-6 py-3 transition-all duration-300 hover:bg-emerald-800 bg-emerald-600 rounded-md">
      {type === "create" ? "Criar" : "Atualizar"}
    </button>
  );
}

export default Submit;
