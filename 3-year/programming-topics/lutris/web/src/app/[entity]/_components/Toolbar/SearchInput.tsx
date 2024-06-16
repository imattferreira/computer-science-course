"use client";
// TODO: create loading state based on transitions API

import { Filter } from "lucide-react";
import { usePathname, useRouter } from "next/navigation";
import { useEffect, useState } from "react";

function SearchInput() {
  const router = useRouter();
  const pathname = usePathname();
  const [query, setQuery] = useState("");

  useEffect(() => {
    let pathnameQs = pathname;

    if (query.length > 3) {
      pathnameQs += `?q=${query}`;
    }

    router.push(pathnameQs);
  }, [query, router, pathname]);

  return (
    <div className="flex rounded-full text-gray-300 ring-2 w-80 ring-zinc-400 px-3 py-2 focus-within:ring-emerald-600 focus-within:ring-2 transition-all items-center text-sm">
      <input
        type="text"
        placeholder="Busque por..."
        className="bg-transparent outline-none w-full"
        onChange={(e) => {
          setQuery(e.target.value);
        }}
      />
      <Filter size={16} />
    </div>
  );
}

export default SearchInput;
