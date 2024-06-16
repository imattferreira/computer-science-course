"use client";

import NextLink from "next/link";
import { usePathname } from "next/navigation";

function GoBack() {
  const pathname = usePathname();

  const link = `/${pathname.split("/")[1]}`;

  return (
    <NextLink
      href={link}
      className="px-6 py-3 transition-all duration-300 bg-zinc-600 hover:bg-zinc-900 rounded-md self-end"
    >
      Voltar
    </NextLink>
  );
}

export default GoBack;
