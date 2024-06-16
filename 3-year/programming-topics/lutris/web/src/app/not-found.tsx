import NextLink from "next/link";
import { ShieldAlert } from "lucide-react";

function NotFound() {
  return (
    <div className="flex items-center flex-col h-full justify-center">
      <h2 className="text-3xl flex gap-x-2 items-center text-gray-100">
        <ShieldAlert size={30} /> Opsss!
      </h2>
      <p className="text-gray-300 mt-2 mb-4">
        Não conseguimos encontrar o que buscava
      </p>
      <NextLink
        href="/"
        className="px-4 py-3 mb-36 bg-emerald-600 hover:bg-emerald-800 transition-all duration-300 rounded-md"
      >
        Voltar para a Home
      </NextLink>
    </div>
  );
}

export default NotFound;
