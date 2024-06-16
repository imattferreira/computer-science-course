"use client";

import NextLink from "next/link";
import { PlusCircle } from "lucide-react";
import { usePathname } from "next/navigation";

function Create() {
  const pathname = usePathname();

  return (
    <NextLink
      href={`${pathname}/criar`}
      aria-label="Criar novo"
      className="flex items-center bg-emerald-600 size-9 rounded-md hover:bg-emerald-800 transition-all duration-300 justify-center"
    >
      <PlusCircle />
    </NextLink>
  );
}

export default Create;
