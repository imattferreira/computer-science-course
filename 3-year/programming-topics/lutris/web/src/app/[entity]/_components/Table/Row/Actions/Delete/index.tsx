"use client";

import dynamic from "next/dynamic";
import { Trash2 } from "lucide-react";
import { Suspense, useState } from "react";
import api from "~/api";
import { Entities } from "~/constants/entities";
import { useRouter } from "next/navigation";

const Modal = dynamic(() => import("./Modal"), { ssr: false });

interface DeleteProps {
  domain: Entities;
  domainId: number;
}

function Delete({ domain, domainId }: DeleteProps) {
  const [isModalOpen, setModalOpen] = useState(false);
  const router = useRouter();

  async function afterConfirm() {
    try {
      await api[domain].delete(domainId);
      router.refresh();
    } catch {
      // do nothing
    }
  }

  function onClose() {
    setModalOpen(false);
  }

  return (
    <>
      {isModalOpen && (
        <Suspense>
          <Modal afterConfirm={afterConfirm} onClose={onClose} />
        </Suspense>
      )}
      <button
        className="bg-red-500 inline-block hover:bg-red-700 transition-all duration-300 p-1 rounded-md"
        onClick={() => {
          setModalOpen(true);
        }}
        aria-label="Deletar"
      >
        <Trash2 size={24} />
      </button>
    </>
  );
}

export default Delete;
