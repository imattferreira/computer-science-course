"use client";
import { useEffect, useRef } from "react";

interface ModalProps {
  afterConfirm: () => unknown;
  onClose: () => void;
}

function Modal({ afterConfirm, onClose }: ModalProps) {
  const dialogRef = useRef<HTMLDialogElement>(null);

  useEffect(() => {
    dialogRef.current?.showModal();
  }, []);

  function goBack() {
    dialogRef.current?.close();
    onClose();
  }

  function onConfirm() {
    afterConfirm();
    onClose();
  }
  return (
    <dialog
      className="bg-zinc-800 text-start text-gray-200 px-6 py-4 rounded-md max-w-lg"
      ref={dialogRef}
    >
      <h2 className="text-2xl mb-4 font-bold">Você tem certeza?</h2>
      <p>
        Você tem certeza que deseja deletar este registro? Após confirmado, a
        ação não poderá mais ser desfeita
      </p>
      <div className="mt-4 flex justify-end gap-x-4">
        <button
          className="px-4 py-3 rounded-md transition-all duration-300 bg-zinc-600 hover:bg-zinc-900"
          onClick={goBack}
        >
          Voltar
        </button>
        <button
          className="px-4 py-3 rounded-md transition-all duration-300 bg-red-500 hover:bg-red-900"
          onClick={onConfirm}
        >
          Confirmar
        </button>
      </div>
    </dialog>
  );
}

export default Modal;
