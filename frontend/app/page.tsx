'use client'
import Button from '@/components/button/Button'

export default function Home() {
  function handleTest() {
    console.log('test')
  }

  return (
    <Button
      title="Test Button"
      handleClick={handleTest}
      containerStyles="w-full py-[16px] rounded-full  bg-amber-300"
      textStyles="text-black font-bold"
    />
  )
}
